workspace(name = "com_fillmore_labs_kafka_sensors")

register_toolchains("//toolchain:toolchain_java21_definition")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "bazel_skylib",
    sha256 = "cd55a062e763b9349921f0f5db8c3933288dc8ba4f76dd9416aac68acee3cb94",
    urls = [
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.5.0/bazel-skylib-1.5.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.5.0/bazel-skylib-1.5.0.tar.gz",
    ],
)

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "c8035e8ae248b56040a65ad3f0b7434712e2037e5dfdcebfe97576e620422709",
    urls = [
        "https://github.com/bazelbuild/rules_go/releases/download/v0.44.0/rules_go-v0.44.0.zip",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.44.0/rules_go-v0.44.0.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "b7387f72efb59f876e4daae42f1d3912d0d45563eac7cb23d1de0b094ab588cf",
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.34.0/bazel-gazelle-v0.34.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.34.0/bazel-gazelle-v0.34.0.tar.gz",
    ],
)

http_archive(
    name = "rules_java",
    sha256 = "eb7db63ed826567b2ceb1ec53d6b729e01636f72c9f5dfb6d2dfe55ad69d1d2a",
    url = "https://github.com/bazelbuild/rules_java/releases/download/7.2.0/rules_java-7.2.0.tar.gz",
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "9bd87b8280ef720d3240514f884e56a712f2218f0d693b48050c836028940a42",
    strip_prefix = "protobuf-25.1",
    url = "https://github.com/protocolbuffers/protobuf/releases/download/v25.1/protobuf-25.1.tar.gz",
)

http_archive(
    name = "rules_proto",
    sha256 = "904a8097fae42a690c8e08d805210e40cccb069f5f9a0f6727cf4faa7bed2c9c",
    strip_prefix = "rules_proto-6.0.0-rc1",
    url = "https://github.com/bazelbuild/rules_proto/releases/download/6.0.0-rc1/rules_proto-6.0.0-rc1.tar.gz",
)

http_archive(
    name = "rules_buf",
    sha256 = "bc2488ee497c3fbf2efee19ce21dceed89310a08b5a9366cc133dd0eb2118498",
    strip_prefix = "rules_buf-0.2.0",
    url = "https://github.com/bufbuild/rules_buf/archive/refs/tags/v0.2.0.zip",
)

http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "b1e80761a8a8243d03ebca8845e9cc1ba6c82ce7c5179ce2b295cd36f7e394bf",
    url = "https://github.com/bazelbuild/rules_docker/releases/download/v0.25.0/rules_docker-v0.25.0.tar.gz",
)

http_archive(
    name = "rules_jvm_external",
    sha256 = "d31e369b854322ca5098ea12c69d7175ded971435e55c18dd9dd5f29cc5249ac",
    strip_prefix = "rules_jvm_external-5.3",
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/5.3/rules_jvm_external-5.3.tar.gz",
)

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "7adaec1cc787ca1519550e71dbd0cb9c149ee1b06f04ba91dda07c12483aae57",
    strip_prefix = "rules_scala-6.3.0",
    url = "https://github.com/bazelbuild/rules_scala/releases/download/v6.3.0/rules_scala-v6.3.0.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "b88c10e3786d80ff93bd15eed4dfab75bddbe3eae2b272bcf29205aabedc1039",
    strip_prefix = "dagger-dagger-2.49",
    url = "https://github.com/google/dagger/archive/dagger-2.49.tar.gz",
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "05c3c3602d25aeda1e9dbc91d3b66e624c1f9fdadf273e5480b489e744ca7269",
    strip_prefix = "buildtools-6.4.0",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/v6.4.0.tar.gz",
)

http_archive(
    name = "contrib_rules_jvm",
    sha256 = "4d62589dc6a55e74bbe33930b826d593367fc777449a410604b2ad7c6c625ef7",
    strip_prefix = "rules_jvm-0.19.0",
    url = "https://github.com/bazel-contrib/rules_jvm/releases/download/v0.19.0/rules_jvm-v0.19.0.tar.gz",
)

http_archive(
    name = "io_bazel_rules_avro",
    patches = ["//third_party/rules_avro:rules_avro.patch"],
    sha256 = "dc103ff94cbd8f99a8fd8e5bd862c54b1ad638d81e7194a0e03cd1b44ba25e87",
    strip_prefix = "rules_avro-cdecec069db96e5bb03127510e3a58f0226efb7b",
    url = "https://github.com/chenrui333/rules_avro/archive/cdecec069db96e5bb03127510e3a58f0226efb7b.tar.gz",
)

# ---

load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")

bazel_skylib_workspace()

# ---

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_rules_dependencies()

go_register_toolchains(go_version = "1.21.5")

# ---

load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

gazelle_dependencies()

# ---

load("@rules_java//java:repositories.bzl", "rules_java_dependencies", "rules_java_toolchains")
load("//toolchain:defs.bzl", "remote_jdk21_repos", "testonly_artifacts")

rules_java_dependencies()

remote_jdk21_repos()

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

rules_buf_toolchains(
    sha256 = "05dfb45d2330559d258e1230f5a25e154f0a328afda2a434348b5ba4c124ece7",
    version = "v1.28.1",
)

# ---

load("@io_bazel_rules_docker//repositories:repositories.bzl", container_repositories = "repositories")

container_repositories()

load("@io_bazel_rules_docker//java:image.bzl", java_repositories = "repositories")

java_repositories()

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

scala_config(scala_version = "2.13.12")

load("@io_bazel_rules_scala//scala:scala.bzl", "rules_scala_setup", "rules_scala_toolchain_deps_repositories")

rules_scala_setup()

rules_scala_toolchain_deps_repositories(fetch_sources = True)

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

avro_repositories(version = "1.11.3")

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

maven_install(
    artifacts = [
        "com.amazon.ion:ion-java:1.11.0",
        "com.fasterxml.jackson.core:jackson-annotations:2.16.0",
        "com.fasterxml.jackson.core:jackson-core:2.16.0",
        "com.fasterxml.jackson.core:jackson-databind:2.16.0",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.16.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.16.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.16.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-joda:2.16.0",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.16.0",
        "com.fasterxml.jackson.module:jackson-module-blackbird:2.16.0",
        "com.fasterxml.jackson.module:jackson-module-parameter-names:2.16.0",
        "com.fasterxml.jackson.module:jackson-module-scala_2.13:2.16.0",
        "com.fasterxml.woodstox:woodstox-core:6.5.1",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.10.1",
        "com.google.errorprone:error_prone_annotations:2.23.0",
        "com.google.flogger:flogger-system-backend:0.8",
        "com.google.flogger:flogger:0.8",
        "com.google.guava:guava:33.0.0-jre",
        "com.google.j2objc:j2objc-annotations:2.8",
        "com.sun.istack:istack-commons-runtime:4.2.0",
        "info.picocli:picocli:4.7.5",
        "io.github.classgraph:classgraph:4.8.165",
        "io.github.toolfactory:jvm-driver:9.6.0",
        "io.helidon.config:helidon-config-object-mapping:4.0.2",
        "io.helidon.config:helidon-config-yaml:4.0.1",
        "io.helidon.config:helidon-config:4.0.2",
        "jakarta.annotation:jakarta.annotation-api:2.1.1",
        "jakarta.inject:jakarta.inject-api:2.0.1",
        "jakarta.xml.bind:jakarta.xml.bind-api:4.0.1",
        "javax.inject:javax.inject:1",
        "org.apache.avro:avro:1.11.3",
        "org.apache.kafka:kafka-clients:3.6.1",
        "org.apache.kafka:kafka-raft:3.6.1",
        "org.apache.kafka:kafka-server-common:3.6.1",
        "org.apache.kafka:kafka-streams:3.6.1",
        "org.apache.kafka:kafka_2.13:3.6.1",
        "org.apache.thrift:libthrift:0.19.0",
        "org.checkerframework:checker-qual:3.42.0",
        "org.checkerframework:checker-util:3.42.0",
        "org.checkerframework:checker:3.42.0",
        "org.glassfish.jaxb:jaxb-runtime:4.0.4",
        "org.immutables:gson:2.10.0",
        "org.immutables:value-annotations:2.10.0",
        "org.immutables:value-processor:2.10.0",
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.21",
        "org.mapstruct:mapstruct-processor:1.5.5.Final",
        "org.mapstruct:mapstruct:1.5.5.Final",
        "org.openjdk.jmh:jmh-core:1.37",
        "org.openjdk.jmh:jmh-generator-annprocess:1.37",
        "org.rocksdb:rocksdbjni:8.9.1",
        "org.slf4j:slf4j-api:2.0.9",
        "org.slf4j:slf4j-jdk14:2.0.9",
        maven.artifact(
            artifact = "avro-tools",
            exclusions = ["*:*"],
            group = "org.apache.avro",
            version = "1.11.3",
        ),
    ] + testonly_artifacts([
        "com.google.testparameterinjector:test-parameter-injector:1.14",
        "com.google.truth.extensions:truth-java8-extension:1.1.5",
        "com.google.truth.extensions:truth-liteproto-extension:1.1.5",
        "com.google.truth.extensions:truth-proto-extension:1.1.5",
        "com.google.truth:truth:1.1.5",
        "com.networknt:json-schema-validator:1.0.88",
        "junit:junit:4.13.2",
        "nl.jqno.equalsverifier:equalsverifier:3.15.4",
        "org.apache.kafka:kafka-streams-test-utils:3.6.1",
        "org.apache.kafka:kafka-streams:jar:test:3.6.1",
        "org.mockito:mockito-core:5.8.0",
        "org.mockito:mockito-errorprone:5.8.0",
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
