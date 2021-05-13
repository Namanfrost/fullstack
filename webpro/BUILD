java_binary(
    name = "ProjectWebpro",
    srcs = glob(["src/main/java/com/newpro/webpro/*.java"]),
     main_class = "com.newpro.webpro.WebproApplication",
     deps = [":service"],
)

java_library(
    name = "service",
    srcs = glob(["src/main/java/com/newpro/webpro/service/*.java"]),
    deps = [":dao",":model"],
)

java_library(
    name = "model",
    srcs = glob(["src/main/java/com/newpro/webpro/model/*.java"]),
)

java_library(
    name = "dao",
    srcs = glob(["src/main/java/com/newpro/webpro/dao/*.java"]),
    deps = [":model"],
)

java_library(
    name = "controller",
    srcs = glob(["src/main/java/com/newpro/webpro/controller/*.java"]),
)