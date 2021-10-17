""" Select container image with an appropriate JRE. """

JAVA_BASE = select({
    "//toolchain:debug": "//third_party/images:java17_debug_base",
    "//toolchain:fastbuild": "//third_party/images:java17_base",
    "//toolchain:optimized": "//third_party/images:java17_base",
    "//conditions:default": "//third_party/images:java17_base",
})
