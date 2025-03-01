import React from 'react';
import './Footer.css';

const Footer =(props)=>{
    return(
        <a id="section-footer">
        <footer id="footer">
		
		<h1>Contact Us</h1>
		<form>      
		  <input name="name" type="text" class="feedback-input" placeholder="Name" />   
		  <input name="email" type="text" class="feedback-input" placeholder="Email" />
		  <textarea name="text" class="feedback-input" placeholder="Comment"></textarea>
		  <input type="submit" value="SUBMIT"/>
		</form>

        </footer>
        </a>
    );
}
export default Footer;