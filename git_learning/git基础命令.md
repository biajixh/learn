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
### 撤销的命令和使用场景
使用场景   |备注              |解决方案
----------|------------------|-----------------------
舍弃工作目录中对一个文件的修改|修改的文件未被暂存或提交|checkout --filename
舍弃工作目录中所有未保存的变更|文件已暂存，但未被提交|reset --hard
合并与某个特定提交(但不含)之间的多个提交||reset commit
移除所有未保存的变更，包含未跟踪的文件|修改的文件未被提交|clean -fd
移除所有已暂存的变更和在某个提交之前提交的工作，但不移除工作目录中的新文件||reset --hard commit
移除之前的工作，但完整保留提交历史记录(前进式回滚)|分支已经被发布，工作目录是干净的|revert commit
从分支历史记录中移除一个单独的提交|修改的文件已经被提交，工作目录是干净的，分支尚未进行发布|rebase --interactive commit
保留之前的工作，但与另一提交合并|选择squash(压缩)选项|rebase --interactive commit