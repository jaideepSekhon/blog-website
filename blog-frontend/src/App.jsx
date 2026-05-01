import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import BlogList from './components/BlogList';
import BlogForm from './components/BlogForm';
import BlogView from './components/BlogView';

function App() {
  return (
    <Router>
      <div className="container">
        <div className="header">
          <h1>Jaideep Sekhon Blog</h1>
          <div className="nav-links">
            <Link to="/">HOME</Link>
            <Link to="/create">NEW POST</Link>
          </div>
        </div>
        
        <Routes>
          <Route path="/" element={<BlogList />} />
          <Route path="/create" element={<BlogForm />} />
          <Route path="/edit/:id" element={<BlogForm />} />
          <Route path="/blog/:id" element={<BlogView />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
