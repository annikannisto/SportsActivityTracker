FROM ubuntu:latest
LABEL authors="anni"

ENTRYPOINT ["top", "-b"]