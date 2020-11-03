import React from 'react';
import { Switch } from 'react-router-dom';

import { PublicRoute } from './routers/PublicRoute';
import { PrivateRoute } from './routers/PrivateRoute';

import SignUp from '@/features/signup';

const App = () => {
    return (
        <div className="form-structor">
            <Switch>
                <PublicRoute
                    exact
                    restricted={false}
                    path={['/', '/signup']}
                    component={SignUp}
                />
            </Switch>
        </div>
    );
};

export default App;
