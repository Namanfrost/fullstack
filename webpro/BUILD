java_binary(
    name = "ProjectWebpro",
    srcs = glob(["src/main/java/com/newpro/webpro/*.java"]),
     main_class = "com.newpro.webpro.WebproApplication",
     deps = [
                ":service",
                "@maven//:org_springframework_boot_spring_boot_autoconfigure",
                 "@maven//:io_springfox_springfox_swagger2",
                 "@maven//:org_springframework_boot_spring_boot"
            ],
)

java_library(
    name = "service",
    srcs = glob(["src/main/java/com/newpro/webpro/service/*.java"]),
    deps = [
                ":dao",
                ":model",
                "@maven//:org_springframework_spring_beans",
                "@maven//:org_springframework_data_spring_data_commons",
                "@maven//:org_springframework_spring_context",
            ],
)

java_library(
    name = "dao",
    srcs = glob(["src/main/java/com/newpro/webpro/dao/*.java"]),
    deps = [
                ":model",
                "@maven//:org_springframework_spring_context",
                "@maven//:org_springframework_data_spring_data_mongodb"
            ],
)


java_library(
    name = "controller",
    srcs = glob(["src/main/java/com/newpro/webpro/controller/*.java"]),
    deps = [
               "@maven//:org_springframework_spring_beans",
               "@maven//:org_springframework_spring_web"
            ],
)

java_library(
    name = "model",
    srcs = glob(["src/main/java/com/newpro/webpro/model/*.java"]),
    deps = [
               "@maven//:org_projectlombok_lombok",
               "@maven//:org_springframework_data_spring_data_commons",
               "@maven//:org_springframework_data_spring_data_mongodb"
           ],
)

