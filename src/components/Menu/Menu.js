import React from "react";
import styled from "styled-components";

/*상품 불러오기*/
import product from '../../JSON/product.json'

function ItemList({item,pcategory}){

    return(
        <>
            {item.pcategory.includes(pcategory)
            ?
            <Frame>
            <Img src='' alt='x' />
            <Name>
                {item.pname}
            </Name>

            <Price>
                {item.pprice}
            </Price>

            </Frame>
            :
            <>
            </>
            }

        </>
    )
}   

function AllItemList({item }){

    return(
        <>
            {item
            ?
            <Frame>
            <Img src='' alt='x' />
            <Name>
                {item.pname}
            </Name>

            <Price>
                {item.pprice}
            </Price>

            </Frame>
            :
            <>
            </>
            }

        </>
    )
} 

export default function Menu({pcategory , ScrollActive , categoryNavigation}){

    const bestSorting = () => (
        product.product.sort((a,b) => {
        return b.pview - a.pview
    }))

    return(
        
        <Positioner className={ScrollActive ? 'flexible' : null}> 
            <CategoryNavigation>
                {categoryNavigation[1] === 'all' ? categoryNavigation[0] : categoryNavigation.join(' > ')}
            </CategoryNavigation>

            {pcategory !== 'animal' ?
            <>
            {product.product.map((item)=>
            <ItemList item={item} id={item.id} pcategory={pcategory} />)}
            </>
                
            :
            <>
            {product.product.map((item)=>
            <AllItemList item={item} id={item.id} />)}
            </>
            }

            {pcategory === 'best' ?
            <>
            {bestSorting().map((item) => 
            <AllItemList item={item} id={item.id} pcategory={pcategory} />)}
            </>
                
            :
            <>
            
            </>
            }
        </Positioner>
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

const CategoryNavigation = styled.div`
padding : 10px;
font-weight : 1000;
font-family : 'tway'
`

const Frame = styled.div`
display: inline-block;
/*부모 요소에 따라 크기 변경 => %*/
width: 30%;
height: 300px;

margin-left : 2.5%;
margin-top : 2.5%;

@media (min-width : 1200px){
        width: 18%;
        
        /*광기*/
        margin-left : 1.666666666666667%;
    }

border-radius: 10px;
background: #fffaf2;
box-shadow: 1px 1px 2px #bebebe, -1px -1px 2px #ffffff;

`

const Img = styled.img`

width: 90%;
height: 65%;
margin: 5%;

display: block;

`

const Name = styled.p`
padding-left : 5px;
font-family : 'tway';
`

const Price = styled.p`
padding-left : 5px;
font-family : 'tway';

`