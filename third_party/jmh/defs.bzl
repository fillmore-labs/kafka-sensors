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
        sha256 = "b73ca4215e3de6a5a94b6a83f2e23f553d299a903492728af57e7a3d8723d7d7",
        strip_prefix = "jmh-fcf18d57852cf00c88a8da5c947c6afc0505cba9",
        urls = ["https://github.com/openjdk/jmh/archive/fcf18d57852cf00c88a8da5c947c6afc0505cba9.tar.gz"],
    )
