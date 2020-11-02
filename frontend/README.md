# frontend
## 1. npm 프로젝트 구성
`npm init`
프로젝트 폴더 생성 후 해당 폴더로 이동하여 npm init 명령어를 통해 npm으로 관리하는 프로젝트를 생성
후 나오는 항목을 default로 사용하려면 -y 옵션 사용
npm init 명령을 하면 package.json 파일이 생성되는데 이 파일을 통해 의존성을 관리함(pom.xml 역할)

## 2. Transcompiler 적용(Babel 사용) : 최신 자바스크립트 문법을 브라우저가 이해할 수 있도록 변환
본 프로젝트에서는 Babel을 사용하고 JSX 코드를 올바르게 트랜스 파일하기 위해 몇 가지 패키지가 필요

### 2.1 babel 설치
yarn add 명령어로 설치

@babel/core : babel core complier
@babel/cli : cli에서 babel 명령어 실행을 위한 패키지
@babel/preset-env : Browserslist를 통해 브라우저를 타겟팅
@babel/preset-react : provides support for JSX etc...
babel-loader : webpack에서 js, jsx 파일의 해석을 위한 loader로 사용,
               Webpack은 Javascript와 Json만 이해하여 Bundling 하는데 jsx 문법을 이해하고 처리 가능한 모듈로 변환 시키는 작업을 담당
babel-plo
`npm i -D == npm install --save-dev` -D 옵션과 --save-dev 옵션은 개발 시에만 필요한 패키지로 설치하는 옵션으로 package.json 파일내의 devDependencies에 기록됨

`npm i -D @babel/core @babel/cli @babel/preset-env @babel/preset-react babel-loader -D`

### 2.2 babel 설정
* 프로젝트 최상단에 .babelrc 파일 생성(webpack.config.js에 설정해도 되지만 분리)
``
{
  "presets": ["@babel/preset-env", "@babel/preset-react"]
}``

## 3. Module Bundler 적용(Webpack 사용) : entry(여러 entry 설정 가능)로부터 모듈의 의존성에 따라 package화 함
### 3.1 webpack 설치
webpack-cli - cli에서 webpack 명령어를 실행하기 위한 패키지

`npm install --save-dev webpack webpack-cli` or `npm i -D webpack webpack-cli`

--save-dev 옵션을 주어 배포 시 제외하는 항목으로 설정(package.json에 작성해도 되지만 webpack.config.js로 분리)
본 프로젝트에서는 webpack.config.js 파일로 설정 

### 3.2 webpack-dev-server 설치
 `npm i -D webpack-dev-server`
프로젝트 빌드 시 빌드한 결과물을 /backend/build/resources/main/static에 넣어 build가 되는데
프로젝트 빌드 전에는 dev 서버를 사용하여 개발의 편의성을 높임

### 3.3 webpack 설정
* 프로젝트 최상단에 webpack.config.js 파일 생성하여 설정
```
const path = require('path');

const config = {
  entry: './src/index.js',
  output: {
    path: path.resolve(__dirname, 'public/dist'),
    publicPath: '/',
    filename: '[name]-bundle.js',
    chunkFilename: '[name]-[chunkhash].js',
  },
  devServer : {
    port: 3000,
  },
  resolve: {
    extensions: ['.js', '.jsx'],
  }
};

module.exports = config;
```

## 4. Extra Loader 및 Plugins 설치
css-loader : css 파일을 모듈로 변 
style-loader
clean-webpack-plugin
   
## 5. api 서버 연동
### 5.1 axios 설치 : react에서 ajax를 요청하는 모듈
axios-util을 통해 모든 요청마다 토근값 가져가도록 함

# References
https://poiemaweb.com/es6-babel-webpack-1
https://dev.to/0xhjohnson/setup-react-babel-7-and-webpack-4-2168

# Error 
## 1. api 호출 시 regeneratorRuntime is not defined 이슈
https://babeljs.io/docs/en/babel-plugin-transform-runtime#corejs
## 2. webpack-dev-server router 사용 시 historyFallback: true 설정 필요