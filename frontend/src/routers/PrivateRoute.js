import React, { useEffect } from 'react';
import { Route, Redirect } from 'react-router-dom';

const PrivateRouteComponent = (props) => {
    if (true) {
        return <Route {...props} />;
    } else {
        return <Redirect to="/signup" />;
    }
};

const PrivateRoute = PrivateRouteComponent;

export { PrivateRoute };
