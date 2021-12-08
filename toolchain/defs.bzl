"""
Helper functions.
"""

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
