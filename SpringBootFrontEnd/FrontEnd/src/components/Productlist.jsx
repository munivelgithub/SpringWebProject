import { Link, useNavigate, useParams } from "react-router-dom";
import { useContext, useEffect } from "react";
import { useState } from "react";
import AppContext from "../Context/Context";
import axios from "../axios";
import UpdateProduct from "./UpdateProduct";

const Productlist = () => {
  const { id } = useParams();
  const { data, addToCart, removeFromCart, cart, refreshData } =
    useContext(AppContext);
  const [product, setProduct] = useState([]);
  const [imageUrl, setImageUrl] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await axios.get(
          `/product_list/${id}`
        );
        setProduct(response.data);

        // console.log(product);

        if (response.data.imageName) {
          fetchImage();
        }
      } catch (error) {
        console.error("Error fetching product:", error);
      }
    };

    const fetchImage = async () => {
      const response = await axios.get(
        `/product_list/${id}/image`,
        { responseType: "blob" }
      );
      setImageUrl(URL.createObjectURL(response.data));
    };

    fetchProduct();
  }, [id]);

  return (
    <>
      <div className="Products-Listing">

        <h1 style={{ marginTop: "100px", padding: "0px 4rem", color: "#fff" }}>Products List</h1>
        <div
          className="grid"
          style={{
            marginTop: "64px",
            display: "grid",
            gridTemplateColumns: "repeat(auto-fit, minmax(250px, 1fr))",
            gap: "20px",
            padding: "20px",
          }}
        >
          {product.length === 0 ? (
            <h2
              className="text-center"
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              No Products Available
            </h2>
          ) : (
            product.map((product) => {
              const { id, brand, name, price, available, imageUrl, category } =
                product;
              const cardStyle = {
                width: "18rem",
                height: "12rem",
                boxShadow: "rgba(0, 0, 0, 0.24) 0px 2px 3px",
                backgroundColor: available ? "#fff" : "#ccc",
              };
              return (
                <div
                  className="card mb-3"
                  style={{
                    width: "250px",
                    height: "360px",
                    boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                    borderRadius: "10px",
                    overflow: "hidden",
                    backgroundColor: available ? "#fff" : "#ccc",
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: 'flex-start',
                    alignItems: 'stretch'
                  }}
                  key={id}
                >
                  <Link
                    to={`/product/${category}/${id}`}
                    style={{ textDecoration: "none", color: "inherit" }}
                  >
                    <img
                      src={imageUrl}
                      alt={name}
                      style={{
                        width: "100%",
                        height: "150px",
                        objectFit: "cover",
                        padding: "5px",
                        margin: "0",
                        borderRadius: "10px 10px 10px 10px",
                      }}
                    />
                    <div
                      className="card-body"
                      style={{
                        flexGrow: 1,
                        display: "flex",
                        flexDirection: "column",
                        justifyContent: "space-between",
                        padding: "10px",
                      }}
                    >
                      <div>
                        <h5
                          className="card-title"
                          style={{ margin: "0 0 10px 0", fontSize: "1.2rem" }}
                        >
                          {name.toUpperCase()}
                        </h5>
                        <i
                          className="card-brand"
                          style={{ fontStyle: "italic", fontSize: "0.8rem" }}
                        >
                          {"~ " + brand}
                        </i>
                      </div>
                      <hr className="hr-line" style={{ margin: "10px 0" }} />
                      <div className="home-cart-price">
                        <h5
                          className="card-text"
                          style={{ fontWeight: "600", fontSize: "1.1rem", marginBottom: '5px' }}
                        >
                          <i className="bi bi-currency-rupee"></i>
                          {price}
                        </h5>
                      </div>
                      <button
                        className="btn-hover color-9"
                        style={{ margin: '10px 25px 0px ' }}
                        onClick={(e) => {
                          e.preventDefault();
                          console.log(available);

                          addToCart(product);
                        }}
                        disabled={!available}
                      >
                        {available ? "Add to Cart" : "Out of Stock"}
                      </button>
                    </div>
                  </Link>
                </div>
              );
            })
          )}
        </div>
      </div>
    </>
  );
};

export default Productlist;