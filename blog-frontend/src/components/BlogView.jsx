import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { API_BASE_URL } from '../config';

function BlogView() {
  const { id } = useParams();
  const [blog, setBlog] = useState(null);

  useEffect(() => {
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
  }, [id]);

  if (!blog) {
    return <div>Loading...</div>;
  }

  return (
    <div className="blog-view">
      <h2>{blog.title}</h2>
      <div className="meta">Post #{id}</div>
      <div className="content">{blog.content}</div>
      <Link to="/"><button className="action-btn">Back to Home</button></Link>
    </div>
  );
}

export default BlogView;
