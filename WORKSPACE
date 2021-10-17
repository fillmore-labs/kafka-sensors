workspace(name = "com_fillmore_labs_kafka_sensors")

register_toolchains("//toolchain:toolchain_java16_definition")

register_toolchains("//toolchain:toolchain_java17_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "2b1641428dff9018f9e85c0384f03ec6c10660d935b750e3fa1492a281a53b0f",
    urls = [
        "https://github.com/bazelbuild/rules_go/releases/download/v0.29.0/rules_go-v0.29.0.zip",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.29.0/rules_go-v0.29.0.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "de69a09dc70417580aabf20a28619bb3ef60d038470c7cf8442fafcf627c21cb",
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.24.0/bazel-gazelle-v0.24.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.24.0/bazel-gazelle-v0.24.0.tar.gz",
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
    sha256 = "9d0f6c9d65320cc1b90965936c5dbb85b857207d551a566f822e272b614eacd5",
    strip_prefix = "protobuf-3.19.0-rc1",
    urls = ["https://github.com/protocolbuffers/protobuf/archive/refs/tags/v3.19.0-rc1.tar.gz"],
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "ae34c344514e08c23e90da0e2d6cb700fcd28e80c02e23e4d5715dddcb42f7b3",
    strip_prefix = "buildtools-4.2.2",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/4.2.2.tar.gz",
)

http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "92779d3445e7bdc79b961030b996cb0c91820ade7ffa7edca69273f404b085d5",
    strip_prefix = "rules_docker-0.20.0",
    urls = ["https://github.com/bazelbuild/rules_docker/releases/download/v0.20.0/rules_docker-v0.20.0.tar.gz"],
)

http_archive(
    name = "rules_jvm_external",
    sha256 = "995ea6b5f41e14e1a17088b727dcff342b2c6534104e73d6f06f1ae0422c2308",
    strip_prefix = "rules_jvm_external-4.1",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.1.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "c4629103cb06caba62d8ab0acde1658b4678c7b3b4ce69a2f33d9e0d7022c4b7",
    strip_prefix = "dagger-dagger-2.39.1",
    urls = ["https://github.com/google/dagger/archive/dagger-2.39.1.tar.gz"],
)

# ---

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_register_toolchains(go_version = "1.17")

go_rules_dependencies()

# ---

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

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

load("@io_bazel_rules_docker//repositories:repositories.bzl", container_repositories = "repositories")

container_repositories()

load("@io_bazel_rules_docker//repositories:deps.bzl", container_deps = "deps")

container_deps()

load("@io_bazel_rules_docker//go:image.bzl", go_repositories = "repositories")

go_repositories()

# ---

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

# ---

load("@com_google_dagger//:workspace_defs.bzl", "DAGGER_ARTIFACTS")

# ---

load("//third_party/avro:defs.bzl", "AVRO_ARTIFACTS")

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
load("@rules_jvm_external//:specs.bzl", "maven")

maven_install(
    artifacts = [
        "com.amazon.ion:ion-java:1.9.0",
        "com.fasterxml.jackson.core:jackson-annotations:2.13.0",
        "com.fasterxml.jackson.core:jackson-core:2.13.0",
        "com.fasterxml.jackson.core:jackson-databind:2.13.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.13.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-joda:2.13.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.0",
        "com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.0",
        "com.google.auto.service:auto-service-annotations:1.0",
        "com.google.auto.service:auto-service:1.0",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.8.8",
        "com.google.errorprone:error_prone_annotations:2.9.0",
        "com.google.flogger:flogger-system-backend:0.7.1",
        "com.google.flogger:flogger:0.7.1",
        "com.google.guava:guava:31.0.1-jre",
        "com.google.j2objc:j2objc-annotations:1.3",
        "com.networknt:json-schema-validator:1.0.62",
        "info.picocli:picocli:4.6.1",
        "io.github.classgraph:classgraph:4.8.128",
        "io.github.toolfactory:narcissus:1.0.6",
        "io.helidon.config:helidon-config-object-mapping:2.3.4",
        "io.helidon.config:helidon-config-yaml:2.3.4",
        "io.helidon.config:helidon-config:2.3.4",
        "jakarta.annotation:jakarta.annotation-api:1.3.5",
        "jakarta.servlet:jakarta.servlet-api:4.0.4",
        "jakarta.validation:jakarta.validation-api:2.0.2",
        "jakarta.ws.rs:jakarta.ws.rs-api:2.1.6",
        "javax.inject:javax.inject:1",
        "org.apache.kafka:kafka-clients:3.0.0",
        "org.apache.kafka:kafka-raft:3.0.0",
        "org.apache.kafka:kafka-streams:3.0.0",
        "org.apache.kafka:kafka_2.13:3.0.0",
        "org.checkerframework:checker-qual:3.18.1",
        "org.checkerframework:checker-util:3.18.1",
        "org.checkerframework:checker:3.18.1",
        "org.immutables:gson:2.9.0-rc1",
        "org.immutables:value-annotations:2.9.0-rc1",
        "org.immutables:value-processor:2.9.0-rc1",
        "org.mapstruct:mapstruct-processor:1.5.0.Beta1",
        "org.mapstruct:mapstruct:1.5.0.Beta1",
        "org.scala-lang:scala-library:2.13.6",
        "org.slf4j:slf4j-api:1.8.0-beta4",
        "org.slf4j:slf4j-jdk14:1.8.0-beta4",
        maven.artifact(
            "com.google.truth",
            "truth",
            "1.1.3",
            testonly = True,
        ),
        maven.artifact(
            "com.google.truth.extensions",
            "truth-java8-extension",
            "1.1.3",
            testonly = True,
        ),
        maven.artifact(
            "com.google.truth.extensions",
            "truth-liteproto-extension",
            "1.1.3",
            testonly = True,
        ),
        maven.artifact(
            "com.google.truth.extensions",
            "truth-proto-extension",
            "1.1.3",
            testonly = True,
        ),
        maven.artifact(
            "junit",
            "junit",
            "4.13.2",
            testonly = True,
        ),
        maven.artifact(
            "org.apache.kafka",
            "kafka-streams-test-utils",
            "3.0.0",
            testonly = True,
        ),
        maven.artifact(
            "org.ow2.asm",
            "asm",
            "9.2",
            testonly = True,
        ),
    ] + DAGGER_ARTIFACTS + AVRO_ARTIFACTS + CONFLUENT_ARTIFACTS + JMH_ARTIFACTS,
    fetch_sources = True,
    maven_install_json = "@com_fillmore_labs_kafka_sensors//:maven_install.json",
    override_targets = {
        # Java EE is now Jakarta EE
        "javax.annotation:javax.annotation-api": ":jakarta_annotation_jakarta_annotation_api",
        "javax.servlet:javax.servlet-api": ":jakarta_servlet_jakarta_servlet_api",
        "javax.validation:validation-api": ":jakarta_validation_jakarta_validation_api",
        "javax.ws.rs:javax.ws.rs-api": ":jakarta_ws_rs_jakarta_ws_rs_api",
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
