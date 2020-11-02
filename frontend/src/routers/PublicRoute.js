import * as React from 'react';
import { Route } from 'react-router-dom';

export const PublicRoute = (props) => {
    return (
        // restricted = false meaning public route
        // restricted = true meaning restricted route
        props.restricted ? <></> : <Route {...props} />
    );
};
