# frontend

1. `npm init`
npm 프로젝트 구성하는 명령어
npm init 후 나오는 항목을 default로 사용하려면 -y 옵션 사용
npm init 명령을 하면 package.json 파일이 생성되는데 이 파일을 통해 의존성을 관리함(pom.xml 역할)

2. `npm install --save-dev webpack webpack-cli` of `npm i -D webpack webpack-cli`
배포 시 js, css, image 등의 파일을 하나로 번들링 하기 위해 필요한 webpack 설치
--save-dev 옵션을 주어 배포 시 제외하는 항목으로 설정
package.json 파일내에 작성해도 되고 동일 폴더내에 webpack.config.js 파일에 작성해도 됨
본 프로젝트에서는 webpack.config.js 파일로 설정 

3. `npm i -D webpack-dev-server`
프로젝트 빌드 시 빌드한 결과물을 /backend/build/resources/main/static에 넣어 build가 되는데
프로젝트 빌드 전에는 dev 서버를 사용하여 개발의 편의성을 높임

1. 인터넷이 되는 환경에서 yarn install 하면 npm_packages 폴더에 압축되서 다운로드 된다 (npm_packages 폴더를 git에 업로드 필요(타 사용자를 위하여...))

## DEV 실행

npm run start or yarn start

## PRODUCTION 배포 (html, js 컨버젼)

npm run build or yarn build