### git & vscode 学习笔记
* git在windows10环境下的使用问题
    + 环境变量的问题：（解决方案）在实际的使用中可以在工作使用的文件夹中打开git bash或者cd至工作文件夹
    - 远程仓库的连接问题：（以github为主）在仓库中要先在bash中config --global user.email 和user.name。之后再连接
    - github发布问题：在vs code中同步好了远程仓库中后，仓库的更改先暂存（add），之后再提交（commit)至分支中。从git工具中可以将本地仓库的分支发布到github，点击后会弹出github登录弹窗。
* tips:感觉git还是命令行比较强大，可以解决不少工具解决不了的问题，还是得好好学习啊