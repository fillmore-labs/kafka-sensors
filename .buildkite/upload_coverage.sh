#!/bin/sh

COVERAGE="$(bazel info output_path)/_coverage/_coverage_report.dat"

echo ":codecov: Upload Coverage"
codecov -f "$COVERAGE" &
PID1=$!

echo ":codeclimate: Upload Coverage"
cc-test-reporter format-coverage -t lcov -o .coverage/codeclimate.json "$COVERAGE"
cc-test-reporter upload-coverage -r "$CC_TEST_REPORTER_ID" -i .coverage/codeclimate.json &
PID2=$!

wait $PID1 $PID2 || true
echo ":trollface: Coverage Upload Done"
