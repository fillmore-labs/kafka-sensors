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
        sha256 = "e03825838180d7a1341fe58c1fd6dcadb6955c9db012224ef91c4f267dae157b",
        strip_prefix = "jmh-730f02b2af7fe23af5f22c33e36070987788b2de",
        urls = ["https://github.com/openjdk/jmh/archive/730f02b2af7fe23af5f22c33e36070987788b2de.tar.gz"],
    )
