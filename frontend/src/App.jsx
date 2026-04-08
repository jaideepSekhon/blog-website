import React, { useState, useEffect } from "react";

function App() {
  const [blogs, setBlogs] = useState([]);
  const [mode, setMode] = useState("list");
  const [currentId, setCurrentId] = useState("");
  const [tt, setTt] = useState("");
  const [cc, setCc] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/blogs")
      .then((r) => r.json())
      .then((r) => {
        setBlogs(r);
      });
  }, []);

  function loadBlogs() {
    fetch("http://localhost:8080/api/blogs")
      .then((r) => r.json())
      .then((r) => {
        setBlogs(r);
      });
  }

  function goCreate() {
    setMode("create");
    setTt("");
    setCc("");
  }

  function createBlog() {
    fetch("http://localhost:8080/api/blogs", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title: tt, content: cc }),
    })
      .then((res) => res.json())
      .then((b) => {
        loadBlogs();
        setMode("list");
      });
  }

  function deleteB(id) {
    fetch("http://localhost:8080/api/blogs/" + id, {
      method: "DELETE",
    }).then(() => {
      loadBlogs();
    });
  }

  function startEdit(b) {
    setMode("edit");
    setCurrentId(b.id);
    setTt(b.title);
    setCc(b.content);
  }

  function updateBlog() {
    fetch("http://localhost:8080/api/blogs/" + currentId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title: tt, content: cc }),
    }).then(() => {
      loadBlogs();
      setMode("list");
    });
  }

  if (mode === "list") {
    return (
      <div className="p-4 max-w-2xl mx-auto">
        <h1 className="text-3xl font-bold mb-4 text-center">My Blogs</h1>
        <div className="flex justify-end mb-4">
          <button
            onClick={goCreate}
            className="bg-black text-white px-4 py-2"
          >
            Create New Blog
          </button>
        </div>
        <div>
          {blogs.map((x) => (
            <div
              key={x.id}
              className="border p-4 mb-4 bg-white"
            >
              <h2 className="text-xl font-bold">{x.title}</h2>
              <p className="mt-2 text-gray-700">{x.content}</p>
              <div className="mt-4 flex gap-2">
                <button
                  onClick={() => startEdit(x)}
                  className="bg-gray-300 px-3 py-1"
                >
                  Edit
                </button>
                <button
                  onClick={() => deleteB(x.id)}
                  className="bg-red-500 text-white px-3 py-1"
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }

  if (mode === "create") {
    return (
      <div className="p-4 max-w-xl mx-auto">
        <h1 className="text-2xl font-bold mb-4">Add Blog</h1>
        <div className="flex flex-col gap-4">
          <input
            className="border p-2"
            placeholder="Title"
            value={tt}
            onChange={(e) => setTt(e.target.value)}
          />
          <textarea
            className="border p-2 h-32"
            placeholder="Content"
            value={cc}
            onChange={(e) => setCc(e.target.value)}
          ></textarea>
          <div className="flex gap-2">
            <button
              onClick={createBlog}
              className="bg-black text-white px-4 py-2"
            >
              Save
            </button>
            <button
              onClick={() => setMode("list")}
              className="bg-gray-300 px-4 py-2"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    );
  }

  if (mode === "edit") {
    return (
      <div className="p-4 max-w-xl mx-auto">
        <h1 className="text-2xl font-bold mb-4">Edit Blog</h1>
        <div className="flex flex-col gap-4">
          <input
            className="border p-2"
            placeholder="Title"
            value={tt}
            onChange={(e) => setTt(e.target.value)}
          />
          <textarea
            className="border p-2 h-32"
            placeholder="Content"
            value={cc}
            onChange={(e) => setCc(e.target.value)}
          ></textarea>
          <div className="flex gap-2">
            <button
              onClick={updateBlog}
              className="bg-black text-white px-4 py-2"
            >
              Update
            </button>
            <button
              onClick={() => setMode("list")}
              className="bg-gray-300 px-4 py-2"
            >
              Cancel
            </button>
          </div>
        </div>
      </div>
    );
  }

  return <div>Loading</div>;
}

export default App;