import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const PrivateRouteComponent = (props) => {
    if (true) {
        // TODO 토큰 있을 때 이동 가능
        return <Route {...props} />;
    } else {
        return <Redirect to="/login" />;
    }
};

const PrivateRoute = PrivateRouteComponent;

export { PrivateRoute };
