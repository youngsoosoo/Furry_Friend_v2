import React from "react";
import styled from "styled-components";

/*상품 불러오기*/
import product from '../../JSON/product.json'

export default function Item(){
    return(
        <>
            <Positioner>

            </Positioner>
        </>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;
position: absolute;
top: 220px;

left : calc(50vw - 400px );
width: calc(100vw - (50vw - 400px) * 2 );

@media (min-width : 1200px){
    left : calc(50vw - 600px );
    width: calc(100vw - (50vw - 600px) * 2 );
}

height: fit-content;

padding : 0px;
padding-bottom : 5rem;
border: 0px;

background-color: #FFFFFF;

&.flexible{
}

`;