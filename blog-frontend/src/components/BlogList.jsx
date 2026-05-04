import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { API_BASE_URL } from '../config';

function BlogList() {
  const [blogs, setBlogs] = useState([]);

  useEffect(() => {
    fetchBlogs();
  }, []);

  const fetchBlogs = async () => {
    try {
      const response = await fetch(API_BASE_URL);
      const data = await response.json();
      setBlogs(data);
    } catch (error) {
      console.error("Error fetching blogs:", error);
    }
  };

  const deleteBlog = async (id) => {
    if (window.confirm("Are you sure you want to delete this post?")) {
      try {
        await fetch(`${API_BASE_URL}/${id}`, {
          method: 'DELETE'
        });
        fetchBlogs();
      } catch (error) {
        console.error("Error deleting blog:", error);
      }
    }
  };

  return (
    <div>
      <table className="blog-table">
        <thead>
          <tr>
            <th width="5%">#</th>
            <th width="50%">Title</th>
            <th width="45%">Actions</th>
          </tr>
        </thead>
        <tbody>
          {blogs.length === 0 ? (
            <tr>
              <td colSpan="3" style={{textAlign: 'center', padding: '20px'}}>No blogs found.</td>
            </tr>
          ) : (
            blogs.map((blog, index) => (
              <tr key={blog.id || index}>
                <td>{index + 1}</td>
                <td>
                  <Link to={`/blog/${blog.id}`} style={{ fontWeight: 'bold', display: 'block', marginBottom: '5px' }}>{blog.title}</Link>
                  <span style={{ fontSize: '0.9em', color: '#666' }}>
                    {blog.content && blog.content.substring(0, 50)}...
                  </span>
                </td>
                <td>
                  <Link to={`/edit/${blog.id}`}>
                    <button className="action-btn">Edit</button>
                  </Link>
                  <button className="action-btn delete-btn" onClick={() => deleteBlog(blog.id)}>Delete</button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default BlogList;
