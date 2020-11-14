import axios from 'axios';

const axiosUtils = axios.create({
    baseURL: '',
});

async function responseValidate(error) {
    const config = error.config;

    // 네트워크 연결 오류
    if (!error.response.data.status.message) {
        alert(error.response.data.status.message);
        return error;
    }

    if (error.response.status === 400) {
        alert('api error', 'API 서버 연결 오류', 'error');
        return error;
    }

    // API 서버 접속 오류
    if (error.response.status === 404 || error.response.status === 504) {
        alert('api error', 'API 서버 연결 오류', 'error');
        return error;
    }

    // 토큰 재발급 처리
}

// TODO 요청 시 토큰
axiosUtils.interceptors.request.use();

// TODO 응답 처리
axiosUtils.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        return Promise.reject(responseValidate(error));
    },
);

export default axiosUtils;
