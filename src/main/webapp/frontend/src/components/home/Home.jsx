import React, { Component } from 'react';
import NavBar from "../navigation/NavBar";
import Footer from "../navigation/Footer";
import { Link } from "react-router-dom";
import './Home.css';


class Home extends Component {
	constructor(props) {
		super(props);
	}
	render() {
		return (
			<React.Fragment>

				<div className="container-fluid px-0 main-container">
					<section id="hero">
						<div className="hero-container">
							<div id="heroCarousel" className="carousel slide carousel-fade" data-ride="carousel">


								<div className="carousel-inner" role="listbox">


									<div className="carousel-item active img-container"
									>
										<div className="carousel-container">
											<div className="carousel-content container ">
												<h2 className="animate__animated animate__fadeInDown">Welcome
													to <span>Tour Planner</span></h2>
												<p className="animate__animated animate__fadeInUp">
													 your ultimate travel companion designed to make trip planning seamless, exciting, and hassle-free! we believe that travel is more than just visiting places; it’s about experiencing cultures, making memories, and embracing new adventures. With a strong network of travel partners, experienced guides, and premium accommodations, we ensure that every journey is comfortable, safe, and unforgettable. 
													 .Start your adventure today and let Tour Planner be your gateway to the world! 🚀🌍✈️ </p>
												<Link to="/tours"
													className="btn-get-started animate__animated animate__fadeInUp scrollto">Checkout Packages
												</Link>
											</div>
										</div>
									</div>


								</div>
							</div>
						</div>
					</section>
					<Footer />
				</div>

			</React.Fragment>

		);
	}
}
export default Home;