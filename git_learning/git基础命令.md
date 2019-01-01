[TOC]
### 基本的git命令
命令                          |用途
------------------------------|------------------------------
git clone url                 |下载一份远程仓库的副本
git init                      |将当前目录转换成一个新的仓库
git status                    |获取仓库状态报告
git add --all                 |将所有修改过的文件和新文件添加至仓库的暂存区
git commit -m "message"       |将所有的暂存文件提交至仓库
git log                       |查看项目历史
git log --oneline             |查看压缩过的项目历史
git branch --list             |列出所有本地分支
git branch --all              |列出所有本地和远程分支
git branch --remotes          |列出所有远程分支
git checkout --track remote_name/branch|创建远程分支的副本，在本地使用
git checkout branch           |切换到另一个本地分支
git checkout -b branch branch_parent|从指定分支创建一个新分支
git add filename(s)           |仅暂存并准备提交指定文件
git add --patch filename      |仅暂存并准备提交部分文件
git reset HEAD filename       |从暂存区移除提出的文件修改
git commit --amend            |使用当前暂存的修改更新之前的提交，并提供一个新的提交消息
git show commit               |输出某个提交的详细信息
git tag tag commit            |为某个提交对象打上标签
git tag                       |列出所有标签
git show tag                  |输出所有带标签提交的详细信息
git remote add remote_name url|创建一个指向远程仓库的引用
git push                      |将当前分支上的修改上传至远程仓库
git remote --verbose          |列出所有可用远程连接中fetch和push命令的url
git push --set-upstream remote_name branch_local branch remote|将本地分支的副本推送至远程服务器
git merge branch              |将当前存储在另一分支的提交并入当前分支
git push --delete remote_name branch_remote|在远程服务器中移除指定名称的分支