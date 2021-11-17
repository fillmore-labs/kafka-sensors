workspace(name = "com_fillmore_labs_kafka_sensors")

register_toolchains("//toolchain:toolchain_java17_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "bazel_skylib",
    sha256 = "c6966ec828da198c5d9adbaa94c05e3a1c7f21bd012a0b29ba8ddbccb2c93b0d",
    urls = [
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.1.1/bazel-skylib-1.1.1.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.1.1/bazel-skylib-1.1.1.tar.gz",
    ],
)

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
    sha256 = "4278baa4320e9e548248c2b2c707495a984b2025d52fda40fba69e326968c0b0",
    strip_prefix = "protobuf-edc8a3182811cc39272549ff894793e1fff4aaab",
    url = "https://github.com/protocolbuffers/protobuf/archive/edc8a3182811cc39272549ff894793e1fff4aaab.tar.gz",
)

http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "92779d3445e7bdc79b961030b996cb0c91820ade7ffa7edca69273f404b085d5",
    strip_prefix = "rules_docker-0.20.0",
    url = "https://github.com/bazelbuild/rules_docker/releases/download/v0.20.0/rules_docker-v0.20.0.tar.gz",
)

http_archive(
    name = "rules_jvm_external",
    sha256 = "995ea6b5f41e14e1a17088b727dcff342b2c6534104e73d6f06f1ae0422c2308",
    strip_prefix = "rules_jvm_external-4.1",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.1.tar.gz",
)

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "6899cddf7407d09266dddcf6faf9f2a8b414de5e2b35ef8b294418f559172f28",
    strip_prefix = "rules_scala-17791a18aa966cdf2babb004822e6c70a7decc76",
    url = "https://github.com/bazelbuild/rules_scala/archive/17791a18aa966cdf2babb004822e6c70a7decc76.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "48cfeb8bc6538e57f6e42de03975c392108b30dac3b7e7a304b555dc8616c673",
    strip_prefix = "dagger-dagger-2.40.1",
    url = "https://github.com/google/dagger/archive/dagger-2.40.1.tar.gz",
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "ae34c344514e08c23e90da0e2d6cb700fcd28e80c02e23e4d5715dddcb42f7b3",
    strip_prefix = "buildtools-4.2.2",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/4.2.2.tar.gz",
)

http_archive(
    name = "google_bazel_common",
    sha256 = "047df1faf990ebaacd7888dcc4a531ff618d8db6f4f80365d4d0ded37108cac0",
    strip_prefix = "bazel-common-d0877c70dab70684355a93ec63b2b2150529c2df",
    url = "https://github.com/google/bazel-common/archive/d0877c70dab70684355a93ec63b2b2150529c2df.tar.gz",
)

# ---

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_register_toolchains(go_version = "1.17.2")

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

scala_config(scala_version = "2.13.7")

load("@io_bazel_rules_scala//scala:scala.bzl", "scala_repositories")

scala_repositories(
    fetch_sources = True,
    overriden_artifacts = {
        "io_bazel_rules_scala_scala_compiler": {
            "artifact": "org.scala-lang:scala-compiler:2.13.7",
            "deps": [
                "@io_bazel_rules_scala_scala_library",
                "@io_bazel_rules_scala_scala_reflect",
            ],
            "sha256": "a450602f03a4686919e60d1aeced549559f1eaffbaf30ffa7987c8d97e3e79a9",
        },
        "io_bazel_rules_scala_scala_library": {
            "artifact": "org.scala-lang:scala-library:2.13.7",
            "sha256": "a8bc08f3b9ff93d0496032bf2677163071b8d212992f41dbf04212e07d91616b",
        },
        "io_bazel_rules_scala_scala_reflect": {
            "artifact": "org.scala-lang:scala-reflect:2.13.7",
            "deps": [
                "@io_bazel_rules_scala_scala_library",
            ],
            "sha256": "a7bc4eca6970083d426a8d081aec313c7b7207d5f83b6724995e34078edc5cbb",
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
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.8.9",
        "com.google.errorprone:error_prone_annotations:2.10.0",
        "com.google.flogger:flogger-system-backend:0.7.1",
        "com.google.flogger:flogger:0.7.1",
        "com.google.guava:guava:31.0.1-jre",
        "com.google.j2objc:j2objc-annotations:1.3",
        "com.networknt:json-schema-validator:1.0.64",
        "info.picocli:picocli:4.6.2",
        "io.github.classgraph:classgraph:4.8.133",
        "io.github.toolfactory:narcissus:1.0.7",
        "io.helidon.config:helidon-config-object-mapping:2.4.0",
        "io.helidon.config:helidon-config-yaml:2.4.0",
        "io.helidon.config:helidon-config:2.4.0",
        "jakarta.annotation:jakarta.annotation-api:1.3.5",
        "javax.inject:javax.inject:1",
        "org.apache.kafka:kafka-clients:3.0.0",
        "org.apache.kafka:kafka-raft:3.0.0",
        "org.apache.kafka:kafka-streams:3.0.0",
        "org.apache.kafka:kafka_2.13:3.0.0",
        "org.apache.thrift:libthrift:0.15.0",
        "org.checkerframework:checker-qual:3.19.0",
        "org.checkerframework:checker-util:3.19.0",
        "org.checkerframework:checker:3.19.0",
        "org.immutables:gson:2.9.0-rc1",
        "org.immutables:value-annotations:2.9.0-rc1",
        "org.immutables:value-processor:2.9.0-rc1",
        "org.mapstruct:mapstruct-processor:1.5.0.Beta1",
        "org.mapstruct:mapstruct:1.5.0.Beta1",
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
            "nl.jqno.equalsverifier",
            "equalsverifier",
            "3.7.2",
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
    maven_install_json = "//:maven_install.json",
    override_targets = {
        "com.google.protobuf:protobuf-java": "@com_google_protobuf//:protobuf_java",
        "com.google.protobuf:protobuf-java-util": "@com_google_protobuf//:protobuf_java_util",
        "javax.annotation:javax.annotation-api": ":jakarta_annotation_jakarta_annotation_api",
        "javax.servlet:javax.servlet-api": ":jakarta_servlet_jakarta_servlet_api",
        "javax.validation:validation-api": ":jakarta_validation_jakarta_validation_api",
        "javax.ws.rs:javax.ws.rs-api": ":jakarta_ws_rs_jakarta_ws_rs_api",
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
