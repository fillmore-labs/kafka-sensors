workspace(name = "com_fillmore_labs_kafka_sensors")

register_toolchains("//toolchain:toolchain_java17_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "bazel_skylib",
    sha256 = "66ffd9315665bfaafc96b52278f57c7e2dd09f5ede279ea6d39b2be471e7e3aa",
    urls = [
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.4.2/bazel-skylib-1.4.2.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.4.2/bazel-skylib-1.4.2.tar.gz",
    ],
)

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "6dc2da7ab4cf5d7bfc7c949776b1b7c733f05e56edc4bcd9022bb249d2e2a996",
    urls = [
        "https://github.com/bazelbuild/rules_go/releases/download/v0.39.1/rules_go-v0.39.1.zip",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.39.1/rules_go-v0.39.1.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "29d5dafc2a5582995488c6735115d1d366fcd6a0fc2e2a153f02988706349825",
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.31.0/bazel-gazelle-v0.31.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.31.0/bazel-gazelle-v0.31.0.tar.gz",
    ],
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "ddf8c9c1ffccb7e80afd183b3bd32b3b62f7cc54b106be190bf49f2bc09daab5",
    strip_prefix = "protobuf-23.2",
    url = "https://github.com/protocolbuffers/protobuf/releases/download/v23.2/protobuf-23.2.tar.gz",
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
    sha256 = "8c3b207722e5f97f1c83311582a6c11df99226e65e2471086e296561e57cc954",
    strip_prefix = "rules_jvm_external-5.1",
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/5.1/rules_jvm_external-5.1.tar.gz",
)

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "141a3919b37c80a846796f792dcf6ea7cd6e7b7ca4297603ca961cd22750c951",
    strip_prefix = "rules_scala-5.0.0",
    url = "https://github.com/bazelbuild/rules_scala/archive/refs/tags/v5.0.0.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "d400ec1161b2b99e7758de4a5948e32be7849c3e81a9c7b8779c48e68e1ef012",
    strip_prefix = "dagger-dagger-2.46.1",
    url = "https://github.com/google/dagger/archive/dagger-2.46.1.tar.gz",
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "977a0bd4593c8d4c8f45e056d181c35e48aa01ad4f8090bdb84f78dca42f47dc",
    strip_prefix = "buildtools-6.1.2",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/v6.1.2.tar.gz",
)

http_archive(
    name = "contrib_rules_jvm",
    sha256 = "2b710518847279f655a18a51a1629b033e4406f29609e73eb07ecfb6f0138d25",
    strip_prefix = "rules_jvm-0.13.0",
    url = "https://github.com/bazel-contrib/rules_jvm/releases/download/v0.13.0/rules_jvm-v0.13.0.tar.gz",
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

go_register_toolchains(go_version = "1.20.5")

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

load("@rules_buf//buf:repositories.bzl", "rules_buf_dependencies", "rules_buf_toolchains")

rules_buf_dependencies()

rules_buf_toolchains(version = "v1.21.0")

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
        "com.amazon.ion:ion-java:1.9.6",
        "com.fasterxml.jackson.core:jackson-annotations:2.15.2",
        "com.fasterxml.jackson.core:jackson-core:2.15.2",
        "com.fasterxml.jackson.core:jackson-databind:2.15.2",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.15.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.15.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-joda:2.15.2",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2",
        "com.fasterxml.jackson.module:jackson-module-blackbird:2.15.2",
        "com.fasterxml.jackson.module:jackson-module-parameter-names:2.15.2",
        "com.fasterxml.jackson.module:jackson-module-scala_2.13:2.15.2",
        "com.fasterxml.woodstox:woodstox-core:6.5.1",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.10.1",
        "com.google.errorprone:error_prone_annotations:2.19.1",
        "com.google.flogger:flogger-system-backend:0.7.4",
        "com.google.flogger:flogger:0.7.4",
        "com.google.guava:guava:32.0.1-jre",
        "com.google.j2objc:j2objc-annotations:2.8",
        "com.sun.istack:istack-commons-runtime:4.2.0",
        "info.picocli:picocli:4.7.4",
        "io.github.classgraph:classgraph:4.8.160",
        "io.github.toolfactory:jvm-driver:9.4.3",
        "io.helidon.config:helidon-config-object-mapping:3.2.1",
        "io.helidon.config:helidon-config-yaml:3.2.1",
        "io.helidon.config:helidon-config:3.2.1",
        "jakarta.annotation:jakarta.annotation-api:2.1.1",
        "jakarta.inject:jakarta.inject-api:2.0.1",
        "jakarta.xml.bind:jakarta.xml.bind-api:4.0.0",
        "javax.inject:javax.inject:1",
        "org.apache.avro:avro:1.11.1",
        "org.apache.kafka:kafka-clients:3.4.1",
        "org.apache.kafka:kafka-raft:3.4.1",
        "org.apache.kafka:kafka-server-common:3.4.1",
        "org.apache.kafka:kafka-streams:3.4.1",
        "org.apache.kafka:kafka_2.13:3.4.1",
        "org.apache.thrift:libthrift:0.18.1",
        "org.checkerframework:checker-qual:3.35.0",
        "org.checkerframework:checker-util:3.35.0",
        "org.checkerframework:checker:3.35.0",
        "org.glassfish.jaxb:jaxb-runtime:4.0.3",
        "org.immutables:gson:2.9.3",
        "org.immutables:value-annotations:2.9.3",
        "org.immutables:value-processor:2.9.3",
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22",
        "org.mapstruct:mapstruct-processor:1.5.5.Final",
        "org.mapstruct:mapstruct:1.5.5.Final",
        "org.openjdk.jmh:jmh-core:1.36",
        "org.openjdk.jmh:jmh-generator-annprocess:1.36",
        "org.rocksdb:rocksdbjni:8.1.1.1",
        "org.slf4j:slf4j-api:2.0.7",
        "org.slf4j:slf4j-jdk14:2.0.7",
        maven.artifact(
            artifact = "avro-tools",
            exclusions = ["*:*"],
            group = "org.apache.avro",
            version = "1.11.1",
        ),
    ] + testonly_artifacts([
        "com.google.testparameterinjector:test-parameter-injector:1.12",
        "com.google.truth.extensions:truth-java8-extension:1.1.4",
        "com.google.truth.extensions:truth-liteproto-extension:1.1.4",
        "com.google.truth.extensions:truth-proto-extension:1.1.4",
        "com.google.truth:truth:1.1.4",
        "com.networknt:json-schema-validator:1.0.83",
        "junit:junit:4.13.2",
        "nl.jqno.equalsverifier:equalsverifier:3.14.2",
        "org.apache.kafka:kafka-streams-test-utils:3.4.1",
        "org.apache.kafka:kafka-streams::test:3.4.1",
        "org.mockito:mockito-core:5.3.1",
        "org.mockito:mockito-errorprone:5.3.1",
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
