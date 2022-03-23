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
        sha256 = "5ff1ca90fbc1fff07733cb9518b36127f0052d7c273b6740e6f9089704925e9b",
        strip_prefix = "jmh-d2669a959ec6a511538846a3af950a5a621cb49c",
        urls = ["https://github.com/openjdk/jmh/archive/d2669a959ec6a511538846a3af950a5a621cb49c.tar.gz"],
        patches = ["//third_party/jmh:pr65.patch"],
    )
