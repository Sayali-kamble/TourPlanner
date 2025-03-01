import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import Swal from 'sweetalert2';
import { useParams, useHistory } from 'react-router-dom';


const BookingForm = () => {
	const { id } = useParams(); // Get the tour ID from the URL
	const history = useHistory();
	const [tour, setTour] = useState(null);
	const [fullName, setFullName] = useState('');
	const [email, setEmail] = useState('');
	const [numberOfPeople, setNumberOfPeople] = useState(1);
	const [bookingDate, setBookingDate] = useState('');

	// Fetch tour data based on the ID
	useEffect(() => {
		Axios.get(`http://localhost:8093/tours-api/tours/${id}`)
			.then(response => {
				setTour(response.data); // Store tour data when it's fetched
			})
			.catch(error => {
				Swal.fire({
					title: 'Error!',
					text: 'Unable to fetch tour data.',
					icon: 'error',
					confirmButtonText: 'OK',
				});
			});
	}, [id]); // Re-fetch when the ID changes

	const handleSubmit = async (event) => {
		event.preventDefault();
		// Create booking object
		const booking = {
			tourId: id,
			tourTitle: tour.title,
			fullName,
			email,
			numberOfPeople,
			bookingDate
		};

		try {
			// Send booking data to the backend
			const response = await Axios.post("http://localhost:8093/bookings/create", booking);
			if (response.status === 200) {
				Swal.fire({
					title: 'Booking Successful!',
					text: 'Your booking has been confirmed!',
					icon: 'success',
					confirmButtonText: 'OK',
				}).then(() => {
					history.push('/'); // Redirect to the home page after successful booking
				});
			}
		} catch (error) {
			Swal.fire({
				title: 'Booking Failed!',
				text: 'Please try again later.',
				icon: 'error',
				confirmButtonText: 'OK',
			});
		}
	};

	if (!tour) {
		return <div>Loading tour data...</div>; // Show loading while tour data is being fetched
	}

	return (
		<div className="booking-form">
			<h2>Book Your Tour: {tour.title}</h2>
			<form onSubmit={handleSubmit}>
				<div className="form-group">
					<label>Full Name</label>
					<input
						type="text"
						className="form-control"
						value={fullName}
						onChange={(e) => setFullName(e.target.value)}
						required
					/>
				</div>

				<div className="form-group">
					<label>Email</label>
					<input
						type="email"
						className="form-control"
						value={email}
						onChange={(e) => setEmail(e.target.value)}
						required
					/>
				</div>

				<div className="form-group">
					<label>Number of People</label>
					<input
						type="number"
						className="form-control"
						value={numberOfPeople}
						onChange={(e) => setNumberOfPeople(e.target.value)}
						required
					/>
				</div>

				<div className="form-group">
					<label>Booking Date</label>
					<input
						type="date"
						className="form-control"
						value={bookingDate}
						onChange={(e) => setBookingDate(e.target.value)}
						required
					/>
				</div>

				<button type="submit" className="btn btn-primary">
					Confirm Booking
				</button>
			</form>
		</div>
	);
};

export default BookingForm;




{/*import React, { useState, useEffect } from "react";
import { useParams, useHistory } from "react-router-dom";
import Axios from "axios";
import Swal from "sweetalert2";

const BookingForm = () => {
    const { tourId } = useParams(); // Get tourId from the URL
    const history = useHistory(); // Hook for navigation

    const [tour, setTour] = useState(null);
    const [fullName, setFullName] = useState("");
    const [email, setEmail] = useState("");
    const [numberOfPeople, setNumberOfPeople] = useState(1);
    const [bookingDate, setBookingDate] = useState("");

    useEffect(() => {
        Axios.get(`http://localhost:8093/tours-api/tours/${tourId}`)
            .then(response => setTour(response.data))
            .catch(error => console.error("Error fetching tour details", error));
    }, [tourId]);

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Basic validations
        if (numberOfPeople < 1) {
            Swal.fire("Invalid Input", "Number of people must be at least 1!", "warning");
            return;
        }
        if (new Date(bookingDate) < new Date()) {
            Swal.fire("Invalid Date", "Please select a future date!", "warning");
            return;
        }

        const booking = { tourId, fullName, email, numberOfPeople, bookingDate };

        try {
            const response = await Axios.post("http://localhost:8093/bookings/create", booking);
            if (response.status === 200 || response.status === 201) {
                Swal.fire("Booking Successful!", "Your booking has been confirmed!", "success")
                    .then(() => history.push("/")); // Redirect to home page
            }
        } catch (error) {
            Swal.fire("Booking Failed!", "Please try again later.", "error");
        }
    };

    return (
        <div className="booking-form">
            <h2>Book Your Tour</h2>
            {tour ? (
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Full Name</label>
                        <input type="text" className="form-control" value={fullName} onChange={(e) => setFullName(e.target.value)} required />
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>

                    <div className="form-group">
                        <label>Number of People</label>
                        <input type="number" className="form-control" value={numberOfPeople} onChange={(e) => setNumberOfPeople(parseInt(e.target.value, 10) || 1)} min="1" required />
                    </div>

                    <div className="form-group">
                        <label>Booking Date</label>
                        <input type="date" className="form-control" value={bookingDate} onChange={(e) => setBookingDate(e.target.value)} required />
                    </div>

                    <button type="submit" className="btn btn-primary">Confirm Booking</button>
                </form>
            ) : (
                <p>Loading tour details...</p>
            )}
        </div>
    );
};

export default BookingForm;*/}





{/*import React, { useState } from 'react';
import Axios from 'axios';
import Swal from 'sweetalert2';

const BookingForm = ({ tourId, tourTitle, price }) => {
	const [fullName, setFullName] = useState('');
	const [email, setEmail] = useState('');
	const [numberOfPeople, setNumberOfPeople] = useState(1);
	const [bookingDate, setBookingDate] = useState('');


	const handleSubmit = async (event) => {
		event.preventDefault();
		// Create booking object
		const booking = {
			tourId,
			tourTitle,
			fullName,
			email,
			numberOfPeople,
			bookingDate
		};

		try {
			// Send booking data to the backend
			const response = await Axios.post("http://localhost:8093/bookings/create", booking);
			if (response.status === 200) {
				Swal.fire({
					title: 'Booking Successful!',
					text: 'Your booking has been confirmed!',
					icon: 'success',
					confirmButtonText: 'OK',
				});
			}
		} catch (error) {
			Swal.fire({
				title: 'Booking Failed!',
				text: 'Please try again later.',
				icon: 'error',
				confirmButtonText: 'OK',
			});
		}
	};

	return (
		<div className="booking-form">
			<h2>Book Your Tour: {tourTitle}</h2>
			<form onSubmit={handleSubmit}>
				<div className="form-group">
					<label>Full Name</label>
					<input
						type="text"
						className="form-control"
						value={fullName}
						onChange={(e) => setFullName(e.target.value)}
						required
					/>
				</div>

				<div className="form-group">
					<label>Email</label>
					<input
						type="email"
						className="form-control"
						value={email}
						onChange={(e) => setEmail(e.target.value)}
						required
					/>
				</div>

				<div className="form-group">
					<label>Number of People</label>
					<input
						type="number"
						className="form-control"
						value={numberOfPeople}
						onChange={(e) => setNumberOfPeople(e.target.value)}
						required
					/>
				</div>

				<div className="form-group">
					<label>Booking Date</label>
					<input
						type="date"
						className="form-control"
						value={bookingDate}
						onChange={(e) => setBookingDate(e.target.value)}
						required
					/>
				</div>

				<button type="submit" className="btn btn-primary">
					Confirm Booking
				</button>
			</form>
		</div>
	);
};

export default BookingForm;*/}
