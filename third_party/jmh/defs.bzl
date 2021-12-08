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
        sha256 = "a26545a4344ef785c80c858c343fbc2d2bd0867256073eb721357d65ebd9f6d0",
        strip_prefix = "jmh-3bc61cb6c62fbca04b87da136a9953c1c3392bd1",
        urls = ["https://github.com/openjdk/jmh/archive/3bc61cb6c62fbca04b87da136a9953c1c3392bd1.tar.gz"],
    )
