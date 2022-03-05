#!/bin/sh

COVERAGE_REPORT="$1"
test -r "$COVERAGE_REPORT" || exit 1

echo "Upload Codecov Coverage"
codecov -f "$COVERAGE_REPORT" &
PID1=$!

echo "Upload Code Climate Coverage"
cc-test-reporter format-coverage -t lcov -o .coverage/codeclimate.json "$COVERAGE_REPORT"
cc-test-reporter upload-coverage -r "$CC_TEST_REPORTER_ID" -i .coverage/codeclimate.json &
PID2=$!

wait $PID1 $PID2 || true
echo "Coverage Upload Done"
