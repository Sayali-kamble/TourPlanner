import React, { Component } from 'react';
import { withRouter } from 'react-router-dom'; // to get access to history
import './TourItem.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import Axios from "axios";
import Swal from "sweetalert2";
import BookingForm from './BookingForm';

class TourItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showBookingForm: false, // State to control the visibility of the BookingForm
        };
    }

    // Handle "Book Now" button click
    handleBookNowClick = () => {
        const { history } = this.props;
        const { id } = this.props.tour; // Use the tour id here
        history.push(`/booking/${id}`); // Redirect to the booking page with the tour id
    };

    // Remove tour (for admin users)
    removeTour = (title) => {
        Axios.delete("http://localhost:8093/tours-api/tours/" + title)
            .then(response => {
                if (response.status === 200) {
                    Swal.fire({
                        title: 'Tour deleted successfully',
                        text: 'Tour Deleted!',
                        icon: 'success',
                        confirmButtonText: 'OK',
                    });
                }
            })
            .catch(error => {
                Swal.fire({
                    title: 'Tour deletion Failed!',
                    text: 'Please try again later',
                    icon: 'error',
                    confirmButtonText: 'OK',
                });
            });
    };

    render() {
        const { title, img, price, included, notIncluded, hotel, route, duration, id } = this.props.tour;
        let deleteBtn = null;

        if (this.props.userType === "admin") {
            deleteBtn = (
                <button className="button-30" onClick={() => { this.removeTour(title) }}>
                    <FontAwesomeIcon icon={faTrash} /> Remove
                </button>
            );
        }

        return (
            <div className="row justify-content-around tour-item">
                <div className="col-md-3">
                    <div className="card">
                        <img className="card-img-top" src={img} alt="Card image cap" />
                    </div>
                </div>
                <div className="col-md-8 mt-4 mt-md-0">
                    <h4 className="package-main-header">{title}</h4>
                    <div className="package-sub-headers">Duration: {duration}</div>
                    <div className="package-sub-headers">Route: {route}</div>
                    <div className="package-sub-headers">Included: {included}</div>
                    <div className="package-sub-headers">Not Included: {notIncluded}</div>
                    <div className="package-sub-headers">Hotel: {hotel}</div>
                    <div className="package-sub-headers">Price: {price}</div>
                    <div className="space">
                        <button className="button-30" onClick={this.handleBookNowClick}>Book Now</button>
                        {deleteBtn}
                    </div>
                </div>
            </div>
        );
    }
}

export default withRouter(TourItem); // Wrap withRouter to get history prop




{/*import React from "react";
import { useHistory } from "react-router-dom";
import Axios from "axios";
import Swal from "sweetalert2";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import "./TourItem.css";

const TourItem = ({ tour, userType }) => {
    const history = useHistory(); // Hook for navigation

    const handleBookNowClick = () => {
        history.push(`/booking/${tour.id}`); // Navigate to BookingForm page
    };

    const removeTour = (title) => {
        Axios.delete(`http://localhost:8093/tours-api/tours/${title}`)
            .then(response => {
                if (response.status === 200) {
                    Swal.fire("Tour deleted successfully", "Tour Removed!", "success");
                }
            })
            .catch(error => {
                Swal.fire("Tour deletion Failed!", "Please try again later", "error");
            });
    };

    return (
        <div className="row justify-content-around tour-item">
            <div className="col-md-3">
                <div className="card">
                    <img className="card-img-top" src={tour.img} alt="Tour" />
                </div>
            </div>
            <div className="col-md-8 mt-4 mt-md-0">
                <h4 className="package-main-header">{tour.title}</h4>
                <div className="package-sub-headers">Duration: {tour.duration}</div>
                <div className="package-sub-headers">Route: {tour.route}</div>
                <div className="package-sub-headers">Included: {tour.included}</div>
                <div className="package-sub-headers">Not Included: {tour.notIncluded}</div>
                <div className="package-sub-headers">Hotel: {tour.hotel}</div>
                <div className="package-sub-headers">Price: {tour.price}</div>
                <div className="space">
                    <button className="button-30" onClick={handleBookNowClick}>Book Now</button>
                    {userType === "admin" && (
                        <button className="button-30" onClick={() => removeTour(tour.title)}>
                            <FontAwesomeIcon icon={faTrash} /> Remove
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
};

export default TourItem;*/}





{/*import React, { Component } from 'react';
import './TourItem.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import Axios from "axios";
import Swal from "sweetalert2";
import BookingForm from './BookingForm';

class TourItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showBookingForm: false, // State to control the visibility of the BookingForm
        };
    }

    // Handle "Book Now" button click
    handleBookNowClick = () => {
        this.setState({ showBookingForm: true }); // Show the booking form
    };

    // Remove tour (for admin users)
    removeTour = (title) => {
        Axios.delete("http://localhost:8093/tours-api/tours/" + title)
            .then(response => {
                if (response.status === 200) {
                    Swal.fire({
                        title: 'Tour deleted successfully',
                        text: 'Tour Deleted!',
                        icon: 'success',
                        confirmButtonText: 'OK',
                    });
                }
            })
            .catch(error => {
                Swal.fire({
                    title: 'Tour deletion Failed!',
                    text: 'Please try again later',
                    icon: 'error',
                    confirmButtonText: 'OK',
                });
            });
    };

    render() {
        const { title, img, price, included, notIncluded, hotel, route, duration } = this.props.tour;
        let deleteBtn = null;

        if (this.props.userType === "admin") {
            deleteBtn = (
                <button className="button-30" onClick={() => { this.removeTour(title) }}>
                    <FontAwesomeIcon icon={faTrash} />Remove
                </button>
            );
        }

        return (
            <div className="row justify-content-around tour-item">
                <div className="col-md-3">
                    <div className="card">
                        <img className="card-img-top" src={img} alt="Card image cap" />
                    </div>
                </div>
                <div className="col-md-8 mt-4 mt-md-0">
                    <h4 className="package-main-header">{title}</h4>
                    <div className="package-sub-headers">Duration: {duration}</div>
                    <div className="package-sub-headers">Route: {route}</div>
                    <div className="package-sub-headers">Included: {included}</div>
                    <div className="package-sub-headers">Not Included: {notIncluded}</div>
                    <div className="package-sub-headers">Hotel: {hotel}</div>
                    <div className="package-sub-headers">Price: {price}</div>
                    <div className="space">
                        <button className="button-30" onClick={this.handleBookNowClick}>Book Now</button>
                        {deleteBtn}
                    </div>
                </div>

                {/* Render BookingForm only when showBookingForm is true */}
                {/*this.state.showBookingForm && <BookingForm tourId={this.props.tour.id} tourTitle={title} price={price} />}
            </div>
        );
    }
}
export default TourItem;*/}

{/*import React, {Component} from 'react';
import './TourItem.css';
import {faEnvelope} from "@fortawesome/free-solid-svg-icons";
import Axios from "axios";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faRecycle} from "@fortawesome/free-solid-svg-icons/faRecycle";
import {faRemoveFormat} from "@fortawesome/free-solid-svg-icons/faRemoveFormat";
import {faTrash} from "@fortawesome/free-solid-svg-icons/faTrash";
import Swal from "sweetalert2";
import BookingForm from './BookingForm';
class TourItem extends Component{
    constructor(props){
        super(props);
    }
    removeTour =(title)=>{
        Axios.delete("http://localhost:8093/tours-api/tours/"+title).then(response=>{

            if(response.status == "200"){
                Swal.fire({
                    title: 'Tour deleted successfully',
                    text: 'Tour Deleted!',
                    icon: 'success',

                    confirmButtonText: 'OK',

                });
            }
        }).catch(error=>{
            Swal.fire({
                title: 'Tour deletion Failed!',
                text: 'Please try again later',
                icon: 'error',

                confirmButtonText: 'OK',

            });
        })
    }
    render() {
        const{title,img,price,included,notIncluded,hotel,route,duration} = this.props.tour;
        let deleteBtn = null;
        if(this.props.userType === "admin"){
            
			deleteBtn = <button class="button-30" onClick={()=>{this.removeTour(title)}}><FontAwesomeIcon icon={faTrash}/>Remove</button>;
        }
        return(

            <div className="row justify-content-around tour-item">
                <div className="col-md-3 ">
                <div className="card " >
                    <img className="card-img-top" src={img} alt="Card image cap"/>

                </div>
                </div>
                <div className="col-md-8 mt-4 mt-md-0">
                      <h4 className="package-main-header">{title}</h4>
                       <div className="package-sub-headers">Duration: {duration}</div>
                    <div className="package-sub-headers">Route: {route}</div>
                    <div className="package-sub-headers">Included: {included}</div>
                    <div className="package-sub-headers">Not Included: {notIncluded}</div>
                    <div className="package-sub-headers">Hotel: {hotel}</div>
                    <div className="package-sub-headers">Price: {price}</div>
					<div className="space"><button class="button-30" >Book Now</button>
					{deleteBtn}
					</div>
                </div>
				{showBookingForm && <BookingForm tourId={props.tour.id} tourTitle={title} price={price} />}
            </div>
        );
    }
}
export default TourItem;*/}
