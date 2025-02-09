import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router';

import Home from './components/Home';
import LoginForm from './components/LoginForm';
import RegistrationForm from './components/RegistrationForm';
import {AuthProvider} from './context/AuthContext';

function App() {
 

  return (
    <AuthProvider>
        <Router>
          <Routes>
            <Route path='/' element={<Home />}/>
            <Route path='/register' element={<RegistrationForm />}/>
            <Route path='/login' element={<LoginForm />}/>
          </Routes>
        </Router>

    </AuthProvider>

  )
}

export default App
