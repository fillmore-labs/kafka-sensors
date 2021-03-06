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
    name = "rules_java",
    sha256 = "8c376f1e4ab7d7d8b1880e4ef8fc170862be91b7c683af97ca2768df546bb073",
    urls = [
        "https://github.com/bazelbuild/rules_java/releases/download/5.0.0/rules_java-5.0.0.1.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_java/releases/download/5.0.0/rules_java-5.0.0.tar.gz",
    ],
)

http_archive(
    name = "remote_java_tools",
    sha256 = "2eede49b2d80135e0ea22180f63df26db2ed4b795c1c041b25cc653d6019fbec",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.7.1/java_tools-v11.7.1.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.7.1/java_tools-v11.7.1.zip",
    ],
)

http_archive(
    name = "remote_java_tools_linux",
    sha256 = "f78077f0c043d0d13c82de0ee4a99753e66bb18ec46e3601fa2a10e7f26798a8",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.7.1/java_tools_linux-v11.7.1.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.7.1/java_tools_linux-v11.7.1.zip",
    ],
)

http_archive(
    name = "remote_java_tools_windows",
    sha256 = "a7086734866505292ee4c206328c73c6af127e69bd51b98c9c186ae4b9b6d2db",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.7.1/java_tools_windows-v11.7.1.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.7.1/java_tools_windows-v11.7.1.zip",
    ],
)

http_archive(
    name = "remote_java_tools_darwin",
    sha256 = "4d6d388b54ad3b9aa35b30dd67af8d71c4c240df8cfb5000bbec67bdd5c53a73",
    urls = [
        "https://github.com/bazelbuild/java_tools/releases/download/java_v11.7.1/java_tools_darwin-v11.7.1.zip",
        "https://mirror.bazel.build/bazel_java_tools/releases/java/v11.7.1/java_tools_darwin-v11.7.1.zip",
    ],
)

http_archive(
    name = "io_bazel_rules_go",
    sha256 = "16e9fca53ed6bd4ff4ad76facc9b7b651a89db1689a2877d6fd7b82aa824e366",
    urls = [
        "https://github.com/bazelbuild/rules_go/releases/download/v0.34.0/rules_go-v0.34.0.zip",
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.34.0/rules_go-v0.34.0.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "501deb3d5695ab658e82f6f6f549ba681ea3ca2a5fb7911154b5aa45596183fa",
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.26.0/bazel-gazelle-v0.26.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.26.0/bazel-gazelle-v0.26.0.tar.gz",
    ],
)

http_archive(
    name = "rules_proto",
    sha256 = "66bfdf8782796239d3875d37e7de19b1d94301e8972b3cbd2446b332429b4df1",
    strip_prefix = "rules_proto-4.0.0",
    url = "https://github.com/bazelbuild/rules_proto/archive/refs/tags/4.0.0.tar.gz",
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "1e958b62debbb46ebefa16d848741d17c88dc018dd57b729c0cd58965380f3f8",
    strip_prefix = "protobuf-21.3",
    url = "https://github.com/protocolbuffers/protobuf/archive/refs/tags/v21.3.tar.gz",
)

http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "b1e80761a8a8243d03ebca8845e9cc1ba6c82ce7c5179ce2b295cd36f7e394bf",
    url = "https://github.com/bazelbuild/rules_docker/releases/download/v0.25.0/rules_docker-v0.25.0.tar.gz",
)

http_archive(
    name = "rules_jvm_external",
    sha256 = "2cd77de091e5376afaf9cc391c15f093ebd0105192373b334f0a855d89092ad5",
    strip_prefix = "rules_jvm_external-4.2",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.2.tar.gz",
)

http_archive(
    name = "io_bazel_rules_scala",
    sha256 = "d1eb5719d7a082c30c3041c8e87a31d41115a9147da718b395b80926f9e47708",
    strip_prefix = "rules_scala-972fdf2b3bda64138db34a630a9910eee96b4d8a",
    url = "https://github.com/bazelbuild/rules_scala/archive/972fdf2b3bda64138db34a630a9910eee96b4d8a.tar.gz",
)

http_archive(
    name = "com_google_dagger",
    sha256 = "d99d8be1dfc66aa7689fdcbc7836cdf0b66b2d25b4eb64af1fdccc27f601eb21",
    strip_prefix = "dagger-dagger-2.42",
    url = "https://github.com/google/dagger/archive/dagger-2.42.tar.gz",
)

http_archive(
    name = "com_github_bazelbuild_buildtools",
    sha256 = "e3bb0dc8b0274ea1aca75f1f8c0c835adbe589708ea89bf698069d0790701ea3",
    strip_prefix = "buildtools-5.1.0",
    url = "https://github.com/bazelbuild/buildtools/archive/refs/tags/5.1.0.tar.gz",
)

http_archive(
    name = "contrib_rules_jvm",
    sha256 = "a939cd04da2deee16131898d91d8e23559dcd1a30a5128beac30a2b01b33c94f",
    strip_prefix = "rules_jvm-0.4.0",
    url = "https://github.com/bazel-contrib/rules_jvm/archive/v0.4.0.tar.gz",
)

http_archive(
    name = "io_bazel_rules_avro",
    sha256 = "487c67372ec6eada06a73c3610b02b949941624bf7e12ff21d11e6da6e966bc2",
    strip_prefix = "rules_avro-eee228b5035b098d4b2dc837cdcac8171c56a8b0",
    url = "https://github.com/chenrui333/rules_avro/archive/eee228b5035b098d4b2dc837cdcac8171c56a8b0.tar.gz",
)

# ---

load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")

bazel_skylib_workspace()

# ---

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")

go_register_toolchains(go_version = "1.18.4")

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

load("@contrib_rules_jvm//:repositories.bzl", "contrib_rules_jvm_deps")

contrib_rules_jvm_deps()

load("@contrib_rules_jvm//:setup.bzl", "contrib_rules_jvm_setup")

contrib_rules_jvm_setup()

# ---

load("@io_bazel_rules_avro//avro:avro.bzl", "avro_repositories")

avro_repositories(version = "1.11.0")

load("@avro//:defs.bzl", pinned_avro_install = "pinned_maven_install")

pinned_avro_install()

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
load("//toolchain:defs.bzl", "testonly_artifacts")

maven_install(
    artifacts = [
        "com.amazon.ion:ion-java:1.9.4",
        "com.fasterxml.jackson.core:jackson-annotations:2.13.3",
        "com.fasterxml.jackson.core:jackson-core:2.13.3",
        "com.fasterxml.jackson.core:jackson-databind:2.13.3",
        "com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.13.3",
        "com.fasterxml.jackson.datatype:jackson-datatype-guava:2.13.3",
        "com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.3",
        "com.fasterxml.jackson.datatype:jackson-datatype-joda:2.13.3",
        "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.3",
        "com.fasterxml.jackson.module:jackson-module-blackbird:2.13.3",
        "com.fasterxml.jackson.module:jackson-module-parameter-names:2.13.3",
        "com.fasterxml.jackson.module:jackson-module-scala_2.13:2.13.3",
        "com.fasterxml.woodstox:woodstox-core:6.3.0",
        "com.google.code.findbugs:jsr305:3.0.2",
        "com.google.code.gson:gson:2.9.0",
        "com.google.errorprone:error_prone_annotations:2.14.0",
        "com.google.flogger:flogger-system-backend:0.7.4",
        "com.google.flogger:flogger:0.7.4",
        "com.google.guava:guava:31.1-jre",
        "com.google.j2objc:j2objc-annotations:1.3",
        "com.sun.activation:jakarta.activation:1.2.2",
        "com.sun.istack:istack-commons-runtime:3.0.12",
        "info.picocli:picocli:4.6.3",
        "io.github.classgraph:classgraph:4.8.149",
        "io.github.toolfactory:narcissus:1.0.7",
        "io.helidon.config:helidon-config-object-mapping:2.5.1",
        "io.helidon.config:helidon-config-yaml:2.5.1",
        "io.helidon.config:helidon-config:2.5.1",
        "jakarta.annotation:jakarta.annotation-api:1.3.5",
        "jakarta.xml.bind:jakarta.xml.bind-api:2.3.3",
        "javax.inject:javax.inject:1",
        "org.apache.avro:avro:1.11.0",
        "org.apache.kafka:kafka-clients:3.2.0",
        "org.apache.kafka:kafka-raft:3.2.0",
        "org.apache.kafka:kafka-streams:3.2.0",
        "org.apache.kafka:kafka_2.13:3.2.0",
        "org.apache.thrift:libthrift:0.16.0",
        "org.checkerframework:checker-qual:3.23.0",
        "org.checkerframework:checker-util:3.23.0",
        "org.checkerframework:checker:3.23.0",
        "org.glassfish.jaxb:jaxb-runtime:2.3.6",
        "org.immutables:gson:2.9.0",
        "org.immutables:value-annotations:2.9.0",
        "org.immutables:value-processor:2.9.0",
        "org.mapstruct:mapstruct-processor:1.5.2.Final",
        "org.mapstruct:mapstruct:1.5.2.Final",
        "org.openjdk.jmh:jmh-core:1.35",
        "org.openjdk.jmh:jmh-generator-annprocess:1.35",
        "org.slf4j:slf4j-api:2.0.0-alpha7",
        "org.slf4j:slf4j-jdk14:2.0.0-alpha7",
    ] + testonly_artifacts([
        "com.google.testparameterinjector:test-parameter-injector:1.8",
        "com.google.truth.extensions:truth-java8-extension:1.1.3",
        "com.google.truth.extensions:truth-liteproto-extension:1.1.3",
        "com.google.truth.extensions:truth-proto-extension:1.1.3",
        "com.google.truth:truth:1.1.3",
        "com.networknt:json-schema-validator:1.0.72",
        "junit:junit:4.13.2",
        "nl.jqno.equalsverifier:equalsverifier:3.10",
        "org.apache.kafka:kafka-streams-test-utils:3.2.0",
        "org.mockito:mockito-core:4.6.1",
        "org.mockito:mockito-errorprone:4.6.1",
    ]) + DAGGER_ARTIFACTS + CONFLUENT_ARTIFACTS,
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
