""" JMH dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

JMH_ARTIFACTS = [
    "org.apache.commons:commons-math3:3.6.1",
    "net.sf.jopt-simple:jopt-simple:5.0.4",
]

def jmh_repositories():
    http_archive(
        name = "jmh",
        build_file = "//third_party/jmh:BUILD.jmh.bazel",
        sha256 = "1637cf4ae816a3618edf6a4bd36735977eeede74437d40177186cead33f12dd6",
        strip_prefix = "jmh-25860b326dd681f4442124d64b2f75aa31cb70ba",
        urls = ["https://github.com/openjdk/jmh/archive/25860b326dd681f4442124d64b2f75aa31cb70ba.tar.gz"],
    )
