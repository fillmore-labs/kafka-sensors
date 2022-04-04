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
        sha256 = "15384e70568b5b4585795147e5a0b15ab56490a5e84f757c78982104cc943dd4",
        strip_prefix = "jmh-b8f50b28c1a2bd758d9410eb21672c92d5eab286",
        url = "https://github.com/openjdk/jmh/archive/b8f50b28c1a2bd758d9410eb21672c92d5eab286.tar.gz",
    )
