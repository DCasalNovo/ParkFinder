import '../driver/Details.css'
import { NavbarStaff } from '../../objects/Navbar';
import { Button } from '../../interactive_items/Button';
import { useEffect, useState } from 'react';
import ParksAdmin from '../../objects/ParksAdmin';

function Admin({
    parks,
}) {

    return (
        <div className='staff_bg'>
            <div className='staff_whitebox'>
                <NavbarStaff link_logo={'/admin'}/>
                <div className='details_options'>
                    <Button buttonStyle={'ditails_button ditails_button_selected'}>Parques</Button>
                </div>

                {<ParksAdmin parks={parks}/>}
            </div>
        </div>
    );
}

export default Admin;



