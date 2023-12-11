"""
Helper functions.
"""

load("@bazel_tools//tools/build_defs/repo:utils.bzl", "maybe")
load("@rules_java//toolchains:remote_java_repository.bzl", "remote_java_repository")
load("@rules_jvm_external//:specs.bzl", "parse")

def testonly_artifacts(artifacts):
    """Makes a list of artifacts test only.

    Args:
        artifacts: A list of Maven artifact coordinates in the form of `group:artifact:version`.

    Returns:
        artifacts with testonly attribute set.
    """
    test_artifacts = []
    for artifact in parse.parse_artifact_spec_list(artifacts):
        artifact["testonly"] = True
        test_artifacts.append(artifact)
    return (test_artifacts)

# https://github.com/bazelbuild/rules_java/blob/master/java/repositories.bzl
def remote_jdk21_repos():
    """Imports OpenJDK 21 repositories."""
    maybe(
        remote_java_repository,
        name = "remotejdk21_linux",
        target_compatible_with = [
            "@platforms//os:linux",
            "@platforms//cpu:x86_64",
        ],
        sha256 = "0c0eadfbdc47a7ca64aeab51b9c061f71b6e4d25d2d87674512e9b6387e9e3a6",
        strip_prefix = "zulu21.28.85-ca-jdk21.0.0-linux_x64",
        urls = [
            "https://mirror.bazel.build/cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-linux_x64.tar.gz",
            "https://cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-linux_x64.tar.gz",
        ],
        version = "21",
    )

    maybe(
        remote_java_repository,
        name = "remotejdk21_linux_aarch64",
        target_compatible_with = [
            "@platforms//os:linux",
            "@platforms//cpu:aarch64",
        ],
        sha256 = "1fb64b8036c5d463d8ab59af06bf5b6b006811e6012e3b0eb6bccf57f1c55835",
        strip_prefix = "zulu21.28.85-ca-jdk21.0.0-linux_aarch64",
        urls = [
            "https://mirror.bazel.build/cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-linux_aarch64.tar.gz",
            "https://cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-linux_aarch64.tar.gz",
        ],
        version = "21",
    )

    maybe(
        remote_java_repository,
        name = "remotejdk21_macos",
        target_compatible_with = [
            "@platforms//os:macos",
            "@platforms//cpu:x86_64",
        ],
        sha256 = "9639b87db586d0c89f7a9892ae47f421e442c64b97baebdff31788fbe23265bd",
        strip_prefix = "zulu21.28.85-ca-jdk21.0.0-macosx_x64",
        urls = [
            "https://mirror.bazel.build/cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-macosx_x64.tar.gz",
            "https://cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-macosx_x64.tar.gz",
        ],
        version = "21",
    )

    maybe(
        remote_java_repository,
        name = "remotejdk21_macos_aarch64",
        target_compatible_with = [
            "@platforms//os:macos",
            "@platforms//cpu:aarch64",
        ],
        sha256 = "2a7a99a3ea263dbd8d32a67d1e6e363ba8b25c645c826f5e167a02bbafaff1fa",
        strip_prefix = "zulu21.28.85-ca-jdk21.0.0-macosx_aarch64",
        urls = [
            "https://mirror.bazel.build/cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-macosx_aarch64.tar.gz",
            "https://cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-macosx_aarch64.tar.gz",
        ],
        version = "21",
    )
    maybe(
        remote_java_repository,
        name = "remotejdk21_win",
        target_compatible_with = [
            "@platforms//os:windows",
            "@platforms//cpu:x86_64",
        ],
        sha256 = "e9959d500a0d9a7694ac243baf657761479da132f0f94720cbffd092150bd802",
        strip_prefix = "zulu21.28.85-ca-jdk21.0.0-win_x64",
        urls = [
            "https://mirror.bazel.build/cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-win_x64.zip",
            "https://cdn.azul.com/zulu/bin/zulu21.28.85-ca-jdk21.0.0-win_x64.zip",
        ],
        version = "21",
    )
