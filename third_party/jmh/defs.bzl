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
        sha256 = "6c2e8b0378f9994ad33e2d457f5fdfcdf184f7dff0070ff50c8883ea84f521a8",
        strip_prefix = "jmh-7051374877b655c96254f446d21df1b359ada598",
        urls = ["https://github.com/openjdk/jmh/archive/7051374877b655c96254f446d21df1b359ada598.tar.gz"],
    )
