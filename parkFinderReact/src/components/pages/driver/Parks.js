import './Parks.css'
import '../../interactive_items/select.css'
import { CompressedParkInfo } from '../../objects/CompressedParkInfo.js';
import { Filter } from '../../objects/Filter';
import { Navbar } from '../../objects/Navbar';
import { useEffect, useState } from 'react';

function Parks({
    displayFilter,
    setDisplayFilter,
    setParkTypeFilter,
    parkTypeFilter
}) {
    
    function success (position){
        console.log(position);
    }
    navigator.geolocation.getCurrentPosition(success)
    

    const [parques,setParques] = useState([])
    const userId = localStorage.getItem('userId')


    useEffect(()=>{
        const requestOptions = {
            method: 'GET',
            headers: { "Access-Control-Allow-Origin": "*" , "Content-Type": "application/json" }
        }
        fetch("http://localhost:8080/apiV1/parques", requestOptions)
            .then(res => res.json())
            .then((result) => { console.log(result);
                setParques(result)
            })
    },[]);


    return (
        <div className='front_page'>
            <Navbar setParkTypeFilter={setParkTypeFilter} setDisplayFilter={() => setDisplayFilter(!displayFilter)}/>
            <div className='parks_content_display'>
                <div className='parks_info_display'>
                    <div className='parks_header'>
                        <h1>Parques</h1>
                        <select className='select' name='Criterion' id='criterion' defaultValue={"default"}>
                            <option className='disabled_selected' value="default" disabled>Sort by</option>
                            <option value='distance'>Distance</option>
                            <option value='price'>Price</option>
                        </select>
                    </div>
                    {parques.map(parque => 
                        <CompressedParkInfo key={parque['id']} parque={parque}/>
                    )}
                    <div className='pageNumb'>
                        <button className='page_button'> {'<<'} </button>
                        <button className='page_button'> 1 </button>
                        <button className='page_button'> 2 </button>
                        <button className='page_button'> 3  </button>
                        <button className='page_button'> {'>>'} </button>
                    </div>
                </div>
                <div className={displayFilter? 'parks_filter_display active': 'parks_filter_display'}>
                    <Filter/>
                </div>
            </div>
        </div>
    );
}

export default Parks;