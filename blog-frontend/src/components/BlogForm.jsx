import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { API_BASE_URL } from '../config';

function BlogForm() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [blog, setBlog] = useState({ title: '', content: '' });

  useEffect(() => {
    if (id) {
      // Fetch existing blog array and get specific blog
      const fetchBlog = async () => {
        try {
          const response = await fetch(`${API_BASE_URL}/${id}`);
          const data = await response.json();
          setBlog(data);
        } catch (error) {
          console.error("Error fetching blog:", error);
        }
      };
      fetchBlog();
    }
  }, [id]);

  const handleChange = (e) => {
    setBlog({ ...blog, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!blog.title || !blog.content) {
      alert("Title and content are required.");
      return;
    }

    try {
      const url = id ? `${API_BASE_URL}/${id}` : API_BASE_URL;
      const method = id ? 'PUT' : 'POST';

      await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(blog)
      });
      navigate('/');
    } catch (error) {
      console.error("Error saving blog:", error);
    }
  };

  return (
    <div>
      <h2 style={{marginTop: 0}}>{id ? 'Edit Post' : 'Create New Post'}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Title</label>
          <input 
            type="text" 
            name="title" 
            value={blog.title} 
            onChange={handleChange} 
            autoComplete="off"
          />
        </div>
        <div className="form-group">
          <label>Content</label>
          <textarea 
            name="content" 
            value={blog.content} 
            onChange={handleChange}
          ></textarea>
        </div>
        <button type="submit" className="submit-btn">{id ? 'Update' : 'Submit'}</button>
      </form>
    </div>
  );
}

export default BlogForm;
