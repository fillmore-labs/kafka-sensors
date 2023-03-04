workspace(name = "com_fillmore_labs_kafka_sensors")

register_toolchains("//toolchain:toolchain_java17_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "bazel_skylib",
    sha256 = "b8a1527901774180afc798aeb28c4634bdccf19c4d98e7bdd1ce79d1fe9aaad7",
    urls = [
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.4.1/bazel-skylib-1.4.1.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.4.1/bazel-skylib-1.4.1.tar.gz",
    ],
)

http_archive(
    name = "rules_java",
    sha256 = "a1f82b730b9c6395d3653032bd7e3a660f9d5ddb1099f427c1e1fe768f92e395",
    url = "https://github.com/bazelbuild/rules_java/releases/download/5.4.1/rules_java-5.4.1.tar.gz",
)

http_archive(
    name = "remote_java_tools",
    sha256 = "af20366f926b1dadf8c084a51936116ef2f0db90e73e94b406c4ad8180f0788d",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.12/java_tools-v11.12.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.12/java_tools-v11.12.zip",
    ],
)

http_archive(
    name = "remote_java_tools_linux",
    sha256 = "37f79597f5b8c1501b9c66ded8ac68c61205ad39ef3ceda0e24fbd0afa3cd97f",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.12/java_tools_linux-v11.12.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.12/java_tools_linux-v11.12.zip",
    ],
)

http_archive(
    name = "remote_java_tools_windows",
    sha256 = "43432ce4814513d6497661b4fede691982b3bdef7a1907808b096291f56a8001",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.12/java_tools_windows-v11.12.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.12/java_tools_windows-v11.12.zip",
    ],
)

http_archive(
    name = "remote_java_tools_darwin",
    sha256 = "aed319892b638efabd08405b8f835770e13e2465d20459876c5f457f2b6426f3",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.12/java_tools_darwin-v11.12.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.12/java_tools_darwin-v11.12.zip",
    ],
)

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "dd926a88a564a9246713a9c00b35315f54cbd46b31a26d5d8fb264c07045f05d",
    urls = [
        "https://github.com/bazelbuild/rules_go/releases/download/v0.38.1/rules_go-v0.38.1.zip",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.38.1/rules_go-v0.38.1.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "ecba0f04f96b4960a5b250c8e8eeec42281035970aa8852dda73098274d14a1d",
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.29.0/bazel-gazelle-v0.29.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.29.0/bazel-gazelle-v0.29.0.tar.gz",
    ],
)

http_archive(
    name = "com_google_googletest",
    sha256 = "2990e587db40e0b37bca2800c2e164f530089370513a3a0076a3bef7bcb537d8",
    strip_prefix = "googletest-2d4f208765af7fa376b878860a7677ecc0bc390a",
    url = "https://github.com/google/googletest/archive/2d4f208765af7fa376b878860a7677ecc0bc390a.tar.gz",
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "782160a6eae4bddfa4061ff5f7dcf04c9ed1494a0f0c6408b4af6b4a31ab9876",
    strip_prefix = "protobuf-22.0",
    url = "https://github.com/protocolbuffers/protobuf/archive/refs/tags/v22.0.tar.gz",
)

http_archive(
    name = "rules_proto",
    sha256 = "dc3fb206a2cb3441b485eb1e423165b231235a1ea9b031b4433cf7bc1fa460dd",
    strip_prefix = "rules_proto-5.3.0-21.7",
    url = "https://github.com/bazelbuild/rules_proto/archive/refs/tags/5.3.0-21.7.tar.gz",
)

http_archive(
    name = "rules_buf",
    sha256 = "cb4a45b0dd892750dd84d36f4ac174b464b1ff2716b0019d8c37ab61365cff0f",
    strip_prefix = "rules_buf-0.1.1",
    url = "https://github.com/bufbuild/rules_buf/archive/refs/tags/v0.1.1.tar.gz",
)

http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "b1e80761a8a8243d03ebca8845e9cc1ba6c82ce7c5179ce2b295cd36f7e394bf",
    url = "https://github.com/bazelbuild/rules_docker/releases/download/v0.25.0/rules_docker-v0.25.0.tar.gz",
)

http_archive(
    name = "rules_jvm_external",
    sha256 = "6e9f2b94ecb6aa7e7ec4a0fbf882b226ff5257581163e88bf70ae521555ad271",
    strip_prefix = "rules_jvm_external-4.5",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.5.tar.gz",
)

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "e519ce3450ba78d39a5ce483ff37e1e2ca1775b6c28dd6f180a63ce88f5d3a9b",
    strip_prefix = "rules_scala-6b5bf4a8a358008d37f6c24c83a4f2fa7663b72f",
    url = "https://github.com/bazelbuild/rules_scala/archive/6b5bf4a8a358008d37f6c24c83a4f2fa7663b72f.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "1071a14af203193685ab7c4165f0aa7ad1e2a9dc46b14f2fe911cab83b728075",
    strip_prefix = "dagger-dagger-2.45",
    url = "https://github.com/google/dagger/archive/dagger-2.45.tar.gz",
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "ca524d4df8c91838b9e80543832cf54d945e8045f6a2b9db1a1d02eec20e8b8c",
    strip_prefix = "buildtools-6.0.1",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/6.0.1.tar.gz",
)

http_archive(
    name = "contrib_rules_jvm",
    sha256 = "548f0583192ff79c317789b03b882a7be9b1325eb5d3da5d7fdcc4b7ca69d543",
    strip_prefix = "rules_jvm-0.9.0",
    url = "https://github.com/bazel-contrib/rules_jvm/archive/v0.9.0.tar.gz",
)

http_archive(
    name = "io_bazel_rules_avro",
    patches = ["//third_party/rules_avro:rules_avro.patch"],
    sha256 = "df0be97b1be6332c5843e3062f8b232351e5b0537946c90e308c194a4f524c87",
    strip_prefix = "rules_avro-03a3148d0af92a430bfa74fed1c8e6abb0685c8c",
    url = "https://github.com/chenrui333/rules_avro/archive/03a3148d0af92a430bfa74fed1c8e6abb0685c8c.tar.gz",
)

# ---

load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")

bazel_skylib_workspace()

# ---

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_rules_dependencies()

go_register_toolchains(go_version = "1.20.1")

# ---

load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

gazelle_dependencies()

# ---

load("@rules_java//java:repositories.bzl", "rules_java_dependencies", "rules_java_toolchains")

rules_java_dependencies()

rules_java_toolchains()

# ---

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

# ---

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()

bind(
    name = "error_prone_annotations",
    actual = "@maven//:com_google_errorprone_error_prone_annotations",
)

bind(
    name = "gson",
    actual = "@maven//:com_google_code_gson_gson",
)

bind(
    name = "guava",
    actual = "@maven//:com_google_guava_guava",
)

bind(
    name = "jsr305",
    actual = "@maven//:com_google_code_findbugs_jsr305",
)

bind(
    name = "j2objc_annotations",
    actual = "@maven//:com_google_j2objc_j2objc_annotations",
)

# ---

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

# ---

load("@rules_buf//buf:repositories.bzl", "rules_buf_dependencies", "rules_buf_toolchains")

rules_buf_dependencies()

rules_buf_toolchains(version = "v1.15.0")

# ---

load("@io_bazel_rules_docker//repositories:repositories.bzl", container_repositories = "repositories")

container_repositories()

load("@io_bazel_rules_docker//repositories:deps.bzl", container_deps = "deps")

container_deps()

load("@io_bazel_rules_docker//go:image.bzl", go_repositories = "repositories")

go_repositories()

# ---

bind(
    name = "io_bazel_rules_scala/dependency/scala/guava",
    actual = "@maven//:com_google_guava_guava",
)

bind(
    name = "io_bazel_rules_scala/dependency/thrift/javax_annotation_api",
    actual = "@maven//:jakarta_annotation_jakarta_annotation_api",
)

load("@io_bazel_rules_scala//:scala_config.bzl", "scala_config")

scala_config(scala_version = "2.13.10")

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")

scala_repositories(
    fetch_sources = True,
    overriden_artifacts = {
        "io_bazel_rules_scala_scala_compiler": {
            "artifact": "org.scala-lang:scala-compiler:2.13.10",
            "deps": [
                "@io_bazel_rules_scala_scala_library",
                "@io_bazel_rules_scala_scala_reflect",
            ],
            "sha256": "2cd4a964ea48e78c91a2a7b19c4fc67a9868728ace5ee966b1d498270b3c3aa7",
        },
        "io_bazel_rules_scala_scala_library": {
            "artifact": "org.scala-lang:scala-library:2.13.10",
            "sha256": "e6ca607c3fce03e8fa38af3374ce1f8bb098e316e8bf6f6d27331360feddb1c1",
        },
        "io_bazel_rules_scala_scala_reflect": {
            "artifact": "org.scala-lang:scala-reflect:2.13.10",
            "deps": [
                "@io_bazel_rules_scala_scala_library",
            ],
            "sha256": "62bd7a7198193c5373a992c122990279e413af3307162472a5d3cbb8ecadb35e",
        },
    },
)

load("@io_bazel_rules_scala//scala:toolchains.bzl", "scala_register_toolchains", "scala_register_unused_deps_toolchains")

scala_register_toolchains()

load("@io_bazel_rules_scala//twitter_scrooge:twitter_scrooge.bzl", "twitter_scrooge")

twitter_scrooge(libthrift = "@maven//:org_apache_thrift_libthrift")

scala_register_unused_deps_toolchains()

# ---

load("@com_google_dagger//:workspace_defs.bzl", "DAGGER_ARTIFACTS")

# ---

load("@contrib_rules_jvm//:repositories.bzl", "contrib_rules_jvm_deps")

contrib_rules_jvm_deps()

load("@contrib_rules_jvm//:setup.bzl", "contrib_rules_jvm_setup")

contrib_rules_jvm_setup()

# ---

load("@io_bazel_rules_avro//avro:avro.bzl", "avro_repositories")

avro_repositories(version = "1.11.1")

# ---

load("//third_party/jsonschema:defs.bzl", "JSONSCHEMA_ARTIFACTS", "jsonschema_repositories")

jsonschema_repositories()

# ---

load("//third_party/confluent:defs.bzl", "CONFLUENT_ARTIFACTS", "confluent_repositories")

confluent_repositories()

# ---

load("//third_party/async_profiler:defs.bzl", "async_profiler_repositories")

async_profiler_repositories()

# ---

load("//third_party/images:defs.bzl", "base_images")

base_images()

# ---

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")
load("//toolchain:defs.bzl", "testonly_artifacts")

maven_install(
    artifacts = [
        "com.amazon.ion:ion-java:1.9.5",
        "com.fasterxml.jackson.core:jackson-annotations:2.14.2",
        "com.fasterxml.jackson.core:jackson-core:2.14.2",
        "com.fasterxml.jackson.core:jackson-databind:2.14.2",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.14.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.14.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.14.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-joda:2.14.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2",
        "com.fasterxml.jackson.module:jackson-module-blackbird:2.14.2",
        "com.fasterxml.jackson.module:jackson-module-parameter-names:2.14.2",
        "com.fasterxml.jackson.module:jackson-module-scala_2.13:2.14.2",
        "com.fasterxml.woodstox:woodstox-core:6.5.0",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.10.1",
        "com.google.errorprone:error_prone_annotations:2.18.0",
        "com.google.flogger:flogger-system-backend:0.7.4",
        "com.google.flogger:flogger:0.7.4",
        "com.google.guava:guava:31.1-jre",
        "com.google.j2objc:j2objc-annotations:2.8",
        "com.sun.istack:istack-commons-runtime:4.1.1",
        "info.picocli:picocli:4.7.1",
        "io.github.classgraph:classgraph:4.8.156",
        "io.github.toolfactory:jvm-driver:9.4.2",
        "io.helidon.config:helidon-config-object-mapping:3.1.2",
        "io.helidon.config:helidon-config-yaml:3.1.2",
        "io.helidon.config:helidon-config:3.1.2",
        "jakarta.annotation:jakarta.annotation-api:2.1.1",
        "jakarta.inject:jakarta.inject-api:2.0.1.MR",
        "jakarta.xml.bind:jakarta.xml.bind-api:4.0.0",
        "javax.inject:javax.inject:1",
        "org.apache.avro:avro:1.11.1",
        "org.apache.kafka:kafka-clients:3.4.0",
        "org.apache.kafka:kafka-raft:3.4.0",
        "org.apache.kafka:kafka-server-common:3.4.0",
        "org.apache.kafka:kafka-streams:3.4.0",
        "org.apache.kafka:kafka_2.13:3.4.0",
        "org.apache.thrift:libthrift:0.18.1",
        "org.checkerframework:checker-qual:3.32.0",
        "org.checkerframework:checker-util:3.32.0",
        "org.checkerframework:checker:3.32.0",
        "org.glassfish.jaxb:jaxb-runtime:4.0.2",
        "org.immutables:gson:2.9.3",
        "org.immutables:value-annotations:2.9.3",
        "org.immutables:value-processor:2.9.3",
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10",
        "org.mapstruct:mapstruct-processor:1.5.3.Final",
        "org.mapstruct:mapstruct:1.5.3.Final",
        "org.openjdk.jmh:jmh-core:1.36",
        "org.openjdk.jmh:jmh-generator-annprocess:1.36",
        "org.rocksdb:rocksdbjni:7.10.2",
        "org.slf4j:slf4j-api:2.0.6",
        "org.slf4j:slf4j-jdk14:2.0.6",
        maven.artifact(
            artifact = "avro-tools",
            exclusions = ["*:*"],
            group = "org.apache.avro",
            version = "1.11.1",
        ),
    ] + testonly_artifacts([
        "com.google.testparameterinjector:test-parameter-injector:1.10",
        "com.google.truth.extensions:truth-java8-extension:1.1.3",
        "com.google.truth.extensions:truth-liteproto-extension:1.1.3",
        "com.google.truth.extensions:truth-proto-extension:1.1.3",
        "com.google.truth:truth:1.1.3",
        "com.networknt:json-schema-validator:1.0.77",
        "junit:junit:4.13.2",
        "nl.jqno.equalsverifier:equalsverifier:3.14",
        "org.apache.kafka:kafka-streams-test-utils:3.4.0",
        "org.apache.kafka:kafka-streams::test:3.4.0",
        "org.mockito:mockito-core:5.1.1",
        "org.mockito:mockito-errorprone:5.1.1",
        "org.mockito:mockito-inline:5.1.1",
    ]) + DAGGER_ARTIFACTS + CONFLUENT_ARTIFACTS + JSONSCHEMA_ARTIFACTS,
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    override_targets = {
        "com.google.protobuf:protobuf-java": "@com_google_protobuf//:protobuf_java",
        "com.google.protobuf:protobuf-java-util": "@com_google_protobuf//:protobuf_java_util",
        "javax.annotation:javax.annotation-api": ":jakarta_annotation_jakarta_annotation_api",
        "javax.servlet:javax.servlet-api": ":jakarta_servlet_jakarta_servlet_api",
        "javax.validation:validation-api": ":jakarta_validation_jakarta_validation_api",
        "javax.ws.rs:javax.ws.rs-api": ":jakarta_ws_rs_jakarta_ws_rs_api",
        "javax.xml.bind:jaxb-api": ":jakarta_xml_bind_jakarta_xml_bind_api",
        "org.scala-lang:scala-compiler": "@io_bazel_rules_scala_scala_compiler",
        "org.scala-lang:scala-library": "@io_bazel_rules_scala_scala_library",
        "org.scala-lang:scala-reflect": "@io_bazel_rules_scala_scala_reflect",
    },
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://repo.maven.apache.org/maven2",
        "https://maven-central-eu.storage-download.googleapis.com/maven2",
    ],
    strict_visibility = True,
)

# ---

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()
