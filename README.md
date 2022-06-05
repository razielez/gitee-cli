# Gitee-cli
封装了`gitee Open API` 的一个命令行工具.

## Build
### Env
- jdk17+
- GraalVM 22.1.0
- Maven

其中 `GraalVM` 自带 `jdk` , 直接使用即可

### GraalVM
- [GraalVM SDK Releases](https://github.com/graalvm/graalvm-ce-builds/releases/)
配置好graalVM 环境后 验证 `java --version`

- 安装 `native-image` 组件
```bash
$ gu installl native-image
```
- 验证
```bash
$ gu list
ComponentId              Version             Component name                Stability                     Origin
---------------------------------------------------------------------------------------------------------------------------------
graalvm                  22.1.0              GraalVM Core                  Supported
js                       22.1.0              Graal.js                      Supported
native-image             22.1.0              Native Image                  Early adopter                 github.com
$ native-image --version
```

### Simple start
```bash
$ ./bin/build.sh
$ ./bin/run.sh 
```

## 实现功能
`Pull Request`
- info
- list
- edit
- create
- close

todo: 其余功能待完善, 自己需要才会封装...

## Ref
- [Gitee Open API](https://gitee.com/api/v5/swagger#/getV5ReposOwnerRepoStargazers?ex=no)
- [GraalVM 官方文档](https://www.graalvm.org/docs/getting-started/)