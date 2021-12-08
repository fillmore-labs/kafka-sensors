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
        sha256 = "77469dca65ec645ac033101c167137f2334d82ea5a6cf4638f6525ee786b85ef",
        strip_prefix = "jmh-44615ed26dbc0548579bd4f23667438e7633f54b",
        urls = ["https://github.com/openjdk/jmh/archive/44615ed26dbc0548579bd4f23667438e7633f54b.tar.gz"],
    )
