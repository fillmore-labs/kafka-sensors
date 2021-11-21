package com.fillmore_labs.kafka.sensors.classcheck;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.LogSites;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import java.io.File;

public final class ClassCheck {
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  private static final int DUPLICATES_LIMIT = 5;

  private ClassCheck() {}

  public static void main(String... args) {
    ClassGraph.CIRCUMVENT_ENCAPSULATION = ClassGraph.CircumventEncapsulationMethod.NARCISSUS;
    var classGraph = new ClassGraph();
    var duplicates = calculateDuplicates(classGraph);
    logResults(duplicates, DUPLICATES_LIMIT);
    logger.atInfo().log("Check finished");
  }

  private static ImmutableMultimap<File, String> calculateDuplicates(ClassGraph classGraph) {
    try (var scan = classGraph.scan();
        var resourceList = scan.getAllResources()) {
      var builder = ImmutableMultimap.<File, String>builder();
      for (var duplicate : resourceList.filter(ClassCheck::resourceFilter).findDuplicatePaths()) {
        try (var duplicates = duplicate.getValue()) {
          for (var resource : duplicates) {
            builder.put(resource.getClasspathElementFile(), duplicate.getKey());
          }
        }
      }
      return builder.build();
    }
  }

  private static void logResults(Multimap<File, String> map, int duplicatesLimit) {
    for (var file : map.keySet()) {
      logger.atWarning().log("Duplicate resource in %s:", file);
      var resources = map.get(file);
      var size = resources.size();
      var logSite = LogSites.logSite();
      resources.stream()
          .limit(duplicatesLimit)
          .forEach(
              resource -> logger.atWarning().withInjectedLogSite(logSite).log(" -> %s", resource));
      if (size > duplicatesLimit) {
        logger.atWarning().log(" -> ... and %d more", size - duplicatesLimit);
      }
    }
  }

  private static boolean resourceFilter(Resource resource) {
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
