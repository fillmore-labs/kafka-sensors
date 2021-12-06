package com.fillmore_labs.kafka.sensors.classcheck;

import com.google.errorprone.annotations.Var;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ResourceList.ResourceFilter;
import java.util.List;
import java.util.Map.Entry;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public final class DuplicatesTest {
  private static final int MAX_DUPLICATEST = 15;

  @Rule public final ErrorCollector errors = new ErrorCollector();

  @Test
  public void testClassPath() {
    var classGraph = createClassGraph();
    var duplicates = calculateDuplicates(classGraph, DuplicatesTest::resourceFilter);

    @Var var count = 0;
    for (var duplicate : duplicates) {
      count++;
      if (count > MAX_DUPLICATEST) {
        break;
      }
      var path = duplicate.getKey();
      var resources = duplicate.getValue().stream().map(Resource::getClasspathElementURI).toList();
      var message = String.format("Duplicate path: %s in %s", path, resources);
      errors.addError(new AssertionError(message));
    }
  }

  private static ClassGraph createClassGraph() {
    ClassGraph.CIRCUMVENT_ENCAPSULATION = ClassGraph.CircumventEncapsulationMethod.NARCISSUS;
    return new ClassGraph();
  }

  private static List<Entry<String, ResourceList>> calculateDuplicates(
      ClassGraph classGraph, ResourceFilter filter) {
    try (var scan = classGraph.scan();
        var resourceList = scan.getAllResources()) {
      return resourceList.filter(filter).findDuplicatePaths();
    }
  }

  private static boolean resourceFilter(Resource resource) {
    var jarFile = resource.getClasspathElementFile();
    if (jarFile != null) {
      var directory = jarFile.getParentFile();
      if (directory != null && directory.getName().equals("java_tools")) { // Bazel hack
        return false;
      }
    }
    var path = resource.getPath();
    if (path.equals("module-info.class")) {
      return false;
    }
    if (!path.endsWith(".class") || path.length() < 7) {
      return false;
    }
    // Check filename is not simply ".class"
    var c = path.charAt(path.length() - 7);
    return c != '/' && c != '.';
  }
}
