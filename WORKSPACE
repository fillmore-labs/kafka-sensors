workspace(name = "com_fillmore_labs_kafka_sensors")

register_toolchains("//toolchain:toolchain_java17_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "bazel_skylib",
    sha256 = "f7be3474d42aae265405a592bb7da8e171919d74c16f082a5457840f06054728",
    urls = [
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.2.1/bazel-skylib-1.2.1.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.2.1/bazel-skylib-1.2.1.tar.gz",
    ],
)

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "f2dcd210c7095febe54b804bb1cd3a58fe8435a909db2ec04e31542631cf715c",
    urls = [
        "https://github.com/bazelbuild/rules_go/releases/download/v0.31.0/rules_go-v0.31.0.zip",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.31.0/rules_go-v0.31.0.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "5982e5463f171da99e3bdaeff8c0f48283a7a5f396ec5282910b9e8a49c0dd7e",
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.25.0/bazel-gazelle-v0.25.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.25.0/bazel-gazelle-v0.25.0.tar.gz",
    ],
)

http_archive(
    name = "rules_proto",
    sha256 = "66bfdf8782796239d3875d37e7de19b1d94301e8972b3cbd2446b332429b4df1",
    strip_prefix = "rules_proto-4.0.0",
    urls = [
        "https://github.com/bazelbuild/rules_proto/archive/refs/tags/4.0.0.tar.gz",
    ],
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "b07772d38ab07e55eca4d50f4b53da2d998bb221575c60a4f81100242d4b4889",
    strip_prefix = "protobuf-3.20.0",
    url = "https://github.com/protocolbuffers/protobuf/archive/refs/tags/v3.20.0.tar.gz",
)

# https://github.com/bazelbuild/rules_docker/issues/2009
http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "59536e6ae64359b716ba9c46c39183403b01eabfbd57578e84398b4829ca499a",
    strip_prefix = "rules_docker-0.22.0",
    url = "https://github.com/bazelbuild/rules_docker/releases/download/v0.22.0/rules_docker-v0.22.0.tar.gz",
)

http_archive(
    name = "rules_jvm_external",
    sha256 = "2cd77de091e5376afaf9cc391c15f093ebd0105192373b334f0a855d89092ad5",
    strip_prefix = "rules_jvm_external-4.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.2.tar.gz",
)

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "17a7930f640a4e21dcb1bf066444f474d1d1f892d6f4e9dcb62402ac92b2c986",
    strip_prefix = "rules_scala-a2b971d85cdf846ed9007eeaf022065797fdb5ec",
    url = "https://github.com/bazelbuild/rules_scala/archive/a2b971d85cdf846ed9007eeaf022065797fdb5ec.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "764b5a3d42d162869b2da3cf5fbf153ccd46475970c37349c4f5dd56bb4534e1",
    strip_prefix = "dagger-dagger-2.41",
    url = "https://github.com/google/dagger/archive/dagger-2.41.tar.gz",
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "7f43df3cca7bb4ea443b4159edd7a204c8d771890a69a50a190dc9543760ca21",
    strip_prefix = "buildtools-5.0.1",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/5.0.1.tar.gz",
)

http_archive(
    name = "google_bazel_common",
    sha256 = "b10c3b19cdc002a223e34ba89271b0ded990ee81b7e4a2e6e55c1b02bfb7741a",
    strip_prefix = "bazel-common-f538027ae7f9133d1796eb6b970fb78eff58e4b5",
    url = "https://github.com/google/bazel-common/archive/f538027ae7f9133d1796eb6b970fb78eff58e4b5.tar.gz",
)

# ---

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_register_toolchains(go_version = "1.18")

go_rules_dependencies()

# ---

load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

gazelle_dependencies()

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

scala_config(scala_version = "2.13.8")

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")

scala_repositories(
    fetch_sources = True,
    overriden_artifacts = {
        "io_bazel_rules_scala_scala_compiler": {
            "artifact": "org.scala-lang:scala-compiler:2.13.8",
            "deps": [
                "@io_bazel_rules_scala_scala_library",
                "@io_bazel_rules_scala_scala_reflect",
            ],
            "sha256": "b248cb6f390ee8bceb912af3da471146fdf003702a173d750f986b1d4a3362e6",
        },
        "io_bazel_rules_scala_scala_library": {
            "artifact": "org.scala-lang:scala-library:2.13.8",
            "sha256": "a0882b82514190c2bac7d1a459872a75f005fc0f3e88b2bc0390367146e35db7",
        },
        "io_bazel_rules_scala_scala_reflect": {
            "artifact": "org.scala-lang:scala-reflect:2.13.8",
            "deps": [
                "@io_bazel_rules_scala_scala_library",
            ],
            "sha256": "fdfbcc92e87f424578b303bcb47e0f55fee990c4b6da0006c9e75879d1e442e4",
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

load("@google_bazel_common//:workspace_defs.bzl", "google_common_workspace_rules")

google_common_workspace_rules()

# ---

load("//third_party/avro:defs.bzl", "AVRO_ARTIFACTS", "avro_repositories")

avro_repositories()

# ---

load("//third_party/confluent:defs.bzl", "CONFLUENT_ARTIFACTS", "confluent_repositories")

confluent_repositories()

# ---

load("//third_party/jmh:defs.bzl", "JMH_ARTIFACTS", "jmh_repositories")

jmh_repositories()

# ---

load("//third_party/async_profiler:defs.bzl", "async_profiler_repositories")

async_profiler_repositories()

# ---

load("//third_party/images:defs.bzl", "base_images")

base_images()

# ---

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("//toolchain:defs.bzl", "testonly_artifacts")

maven_install(
    artifacts = [
        "com.amazon.ion:ion-java:1.9.3",
        "com.fasterxml.jackson.core:jackson-annotations:2.13.2",
        "com.fasterxml.jackson.core:jackson-core:2.13.2",
        "com.fasterxml.jackson.core:jackson-databind:2.13.2.1",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.13.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-joda:2.13.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.2",
        "com.fasterxml.jackson.module:jackson-module-blackbird:2.13.2",
        "com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.2",
        "com.fasterxml.woodstox:woodstox-core:6.2.8",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.9.0",
        "com.google.errorprone:error_prone_annotations:2.11.0",
        "com.google.flogger:flogger-system-backend:0.7.4",
        "com.google.flogger:flogger:0.7.4",
        "com.google.guava:guava:31.1-jre",
        "com.google.j2objc:j2objc-annotations:1.3",
        "com.sun.activation:jakarta.activation:1.2.2",
        "com.sun.istack:istack-commons-runtime:3.0.12",
        "info.picocli:picocli:4.6.3",
        "io.github.classgraph:classgraph:4.8.143",
        "io.github.toolfactory:narcissus:1.0.7",
        "io.helidon.config:helidon-config-object-mapping:3.0.0-M1",
        "io.helidon.config:helidon-config-yaml:3.0.0-M1",
        "io.helidon.config:helidon-config:3.0.0-M1",
        "jakarta.annotation:jakarta.annotation-api:1.3.5",
        "jakarta.xml.bind:jakarta.xml.bind-api:2.3.3",
        "javax.inject:javax.inject:1",
        "org.apache.kafka:kafka-clients:3.1.0",
        "org.apache.kafka:kafka-raft:3.1.0",
        "org.apache.kafka:kafka-streams:3.1.0",
        "org.apache.kafka:kafka_2.13:3.1.0",
        "org.apache.thrift:libthrift:0.16.0",
        "org.checkerframework:checker-qual:3.21.3",
        "org.checkerframework:checker-util:3.21.3",
        "org.checkerframework:checker:3.21.3",
        "org.glassfish.jaxb:jaxb-runtime:2.3.6",
        "org.immutables:gson:2.9.0",
        "org.immutables:value-annotations:2.9.0",
        "org.immutables:value-processor:2.9.0",
        "org.mapstruct:mapstruct-processor:1.5.0.RC1",
        "org.mapstruct:mapstruct:1.5.0.RC1",
        "org.slf4j:slf4j-api:2.0.0-alpha7",
        "org.slf4j:slf4j-jdk14:2.0.0-alpha7",
    ] + testonly_artifacts([
        "com.google.testparameterinjector:test-parameter-injector:1.8",
        "com.google.truth.extensions:truth-java8-extension:1.1.3",
        "com.google.truth.extensions:truth-liteproto-extension:1.1.3",
        "com.google.truth.extensions:truth-proto-extension:1.1.3",
        "com.google.truth:truth:1.1.3",
        "com.networknt:json-schema-validator:1.0.67",
        "junit:junit:4.13.2",
        "nl.jqno.equalsverifier:equalsverifier:3.10",
        "org.apache.kafka:kafka-streams-test-utils:3.1.0",
        "org.mockito:mockito-core:4.4.0",
        "org.mockito:mockito-errorprone:4.4.0",
    ]) + DAGGER_ARTIFACTS + AVRO_ARTIFACTS + CONFLUENT_ARTIFACTS + JMH_ARTIFACTS,
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    override_targets = {
        "com.google.protobuf:protobuf-java": "@com_google_protobuf//:protobuf_java",
        "com.google.protobuf:protobuf-java-util": "@com_google_protobuf//:protobuf_java_util",
        "jakarta.activation:jakarta.activation-api": ":com_sun_activation_jakarta_activation",
        "javax.annotation:javax.annotation-api": ":jakarta_annotation_jakarta_annotation_api",
        "javax.servlet:javax.servlet-api": ":jakarta_servlet_jakarta_servlet_api",
        "javax.validation:validation-api": ":jakarta_validation_jakarta_validation_api",
        "javax.ws.rs:javax.ws.rs-api": ":jakarta_ws_rs_jakarta_ws_rs_api",
        "javax.xml.bind:jaxb-api": ":jakarta_xml_bind_jakarta_xml_bind_api",
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
