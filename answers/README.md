デモ回答集
---
このディレクトリには、本書でマニフェストファイルやアプリケーションのソースコードの編集が指示された箇所に対して、編集後のファイル群が格納されています。ご自身で編集した内容が正しいこと確認するなどの用途で、ご利用下さい。

### ディレクトリ構成
回答のファイルは項ごとにディレクトリを分け、その項での作業内容を反映したものをそれぞれ格納してあります。
ただし、同じ項の配下に複数のセクションがあり、それぞれでまとまった作業単位となっている場合は、その作業単位ごとにさらにディレクトリを分けている場合があります。

項、セクションとディレクトリ名の対応関係は、下表のとおりです。

| 項、セクション | ディレクトリ名 |
| - | - |
| 3-3-1 アプリケーションの修正 | 3-3-1 |
| 3-3-2 Dockerfileの作成とコンテナのビルド | 3-3-2 |
| 4-2-1 データベースのデプロイ | 4-2-1 |
| 4-2-2 アプリケーションのデプロイ | 4-2-2 |
| 5-3-1 ローリングアップデート用のアプリケーションの準備 | 5-3-1 |
| 5-3-2 ローリングアップデート用マニフェストファイルの修正 | 5-3-2 |
| 5-5-2 マイクロサービス化の実施 > プロキシの挿入 | 5-5-2-1 |
| 5-5-2 マイクロサービス化の実施 > 読み取り操作の切り替え | 5-5-2-2 |
| 5-5-2 マイクロサービス化の実施 > 書き込み操作の切り替え > Envoyの設定変更（1回目） | 5-5-2-3-1 |
| 5-5-2 マイクロサービス化の実施 > 書き込み操作の切り替え > Envoyの設定変更（2回目） | 5-5-2-3-2 |
| 5-5-2 マイクロサービス化の実施 > Reviewsサービスからの機能の削除 | 5-5-2-4 |
| 6-3-1 ロギングエージェントのデプロイ | 6-3-1 |
| 6-3-4 Bookinfoへのオブザーバビリティの適用 > 構造化ロギングの計装 | 6-3-4-1 |
| 6-3-4 Bookinfoへのオブザーバビリティの適用 > 分散トレースの計装 | 6-3-4-2 |
| 6-3-4 Bookinfoへのオブザーバビリティの適用 > メトリクスの計装 | 6-3-4-3 |
| 6-3-4 Bookinfoへのオブザーバビリティの適用 > クラスタへの反映 | 6-3-4-4 |
| 7-2-1 Podの起動に備える | 7-2-1 |
| 7-2-2 コンテナの終了に備える | 7-2-2 |

### マニフェストファイル、ソースコードの変更の反映
回答のファイルは以下のコマンドで本体のファイルに反映できます。

```console
## devcontainer環境のターミナルで実行
$ cp -r ${WORKSPACE_FOLDER}/answers/[回答ディレクトリ名]/* ${WORKSPACE_FOLDER}
```

例）

```console
$ cp -r ${WORKSPACE_FOLDER}/answers/3-3-1/* ${WORKSPACE_FOLDER}
```

### 注意事項
本回答集のファイル群は、マニフェストファイルとソースコードに関してのみ提供しており、書籍における全ての作業を網羅しているわけではありません。
マニフェストファイルやソースコードの編集以外の作業については、書籍の指示に従って実施する必要があります。

これは、例えば以下のような作業が該当します。

- EKSクラスタの作成
- クラスタへのSecretオブジェクトの作成
- コンテナイメージのビルドとプッシュ

回答集に含まれるファイルは、GitLabのアカウント名を `myaccount` としたものとして作成してあります。
これは、コンテナイメージ名に含まれるGitLabアカウント名の階層に反映されています。
実際にクラスタに適用する際には、事前にご自身のアカウント名に修正して下さい。


5-5-2「Reviewsサービスからの機能の削除」について
---
書籍では、Ratings関連の機能をRatingサービスに移行した後、Reviewsサービスから当該機能を削除する手順については触れていません。ここでその手順を解説します。

### 1. マニフェストとソースコードを編集する
「5-5-2-4」ディレクトリにあるファイルを反映します。この変更には、以下の内容が含まれます。

- ReviewsサービスからRating関連のAPIを削除
- Product PageサービスからのReviews、Ratingサービスの呼び出しを、プロキシを通さないように修正
- マニフェストファイルの更新

```console
## コードの修正
$ cp -r ${WORKSPACE_FOLDER}/answers/5-5-2-4/* ${WORKSPACE_FOLDER}

## Reviewsサービスで不要になるファイルの削除
$ rm ${WORKSPACE_FOLDER}/src/reviews/src/main/java/com/bookinfo/reviews/api/Rating.java

## GitLabアカウント名をご自身のものに修正
$ GITLAB_ACCOUNT_NAME=youraccount

$ sed -i -e "s/myaccount/${GITLAB_ACCOUNT_NAME}/" ${WORKSPACE_FOLDER}/manifests/{productpage,reviews}/bookinfo-*.yaml
``````

### 2. Reviewsサービスを更新する
修正されたReviewsサービスのコンテナをビルド、プッシュします。

```console
$ cd ${WORKSPACE_FOLDER}/src/reviews

$ REVIEWS_IMAGE_NAME=registry.gitlab.com/${GITLAB_ACCOUNT_NAME}/bookinfo-microservices/reviews:chap05-2

$ docker build ${REVIEWS_IMAGE_NAME} .

$ docker push ${REVIEWS_IMAGE_NAME}
```

次に、Reviewsサービスの更新をクラスタに反映します。

```console
$ cd ${WORKSPACE_FOLDER}/manifests

$ kubectl apply -f reviews -l app=reviews
```

### 3. Product Pageサービスを更新する
修正されたProduct pageサービスのコンテナをビルド、プッシュします。

```console
$ cd ${WORKSPACE_FOLDER}/src/productpage

$ PRODUCTPAGE_IMAGE_NAME=registry.gitlab.com/${GITLAB_ACCOUNT_NAME}/bookinfo-microservices/productpage:chap05

$ docker build ${PRODUCTPAGE_IMAGE_NAME} .

$ docker push ${PRODUCTPAGE_IMAGE_NAME}
```

次に、ProductPageサービスの更新をクラスタに反映します。

```console
$ cd ${WORKSPACE_FOLDER}/manifests

$ kubectl apply -f productpage
```

### 4. プロキシを削除する
この時点で、Product Pageサービスはプロキシを通さずに直接Reviews、Ratingsサービスを呼び出すようになっています。
最後に、不要になったプロキシを削除します。

```console
$ kubectl delete -f rearchitect/bookinfo-strangler-proxy.yaml
```


以上。

