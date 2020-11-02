import React from 'react';
import { Route, Link, Switch } from 'react-router-dom';

import { PublicRoute } from './routers/PublicRoute';
import { PrivateRoute } from './routers/PrivateRoute';

import { LoginFeatures, SignupFeatures } from '@/features/member';

const App = () => {
    return (
        <div>
            <Switch>
                <PublicRoute
                    exact
                    restricted={false}
                    path={['/', '/login']}
                    component={LoginFeatures}
                />
                <PublicRoute
                    exact
                    restricted={false}
                    path="/signup"
                    component={SignupFeatures}
                />
            </Switch>
        </div>
    );
};

export default App;
