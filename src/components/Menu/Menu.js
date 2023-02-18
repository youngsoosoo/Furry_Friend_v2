import React ,{useEffect, useState} from "react";
import styled from "styled-components";
import {Link} from 'react-router-dom'
/*상품 불러오기*/
import product from '../../JSON/product.json'
import productImg from '../../JSON/productImg.json'
/* ItemPagination */
import Pagination from './Paging';

function ItemList({item,key,array,setArray}){

    setArray(array)
    
    return(
        <>
            {item
            ?
            <>
            <StyledLink to={`/ItemDetail/${item.pcategory}/${item.pid}`}>

            <Frame>
            <Img src={productImg.Img[item.pid].src1} alt='x' />
            <Name>
                {item.pname}
            </Name>

            <Price>
                {item.pprice}
            </Price>

            </Frame>
            </StyledLink>
            </>
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
            
            <StyledLink to={`/ItemDetail/${item.pcategory}/${item.pid}`}>
                <Frame>
                
                <Img src={productImg.Img[item.pid].src1} alt='x' />
                <Name>
                    {item.pname}
                </Name>

                <Price>
                    {item.pprice}
                </Price>

                </Frame>
            </StyledLink>
            
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

    const [array,setArray] = useState()
    useEffect(()=>{
        console.log(array)
    },[array])
    
    // const [items, setItems] = React.useState([]) //리스트에 나타낼 아이템
    // const [count, setCount] = React.useState(0); //아이템 총 개수
    // const [currentpage, setCurrentpage] = React.useState(1); //현재페이지
    // const [postPerPage] = React.useState(1); //페이지당 아이템 개수

    // const [indexOfLastPost, setIndexOfLastPost] = React.useState(0);
    // const [indexOfFirstPost, setIndexOfFirstPost] = React.useState(0);
    // const [currentPosts, setCurrentPosts] = React.useState(0);

    // //items호출

    // React.useEffect(() => {
    // setCount(array.length);
    // setIndexOfLastPost(currentpage * postPerPage);
    // setIndexOfFirstPost(indexOfLastPost - postPerPage);
    // setCurrentPosts(array.slice(indexOfFirstPost, indexOfLastPost));
    // }, [currentpage, indexOfFirstPost, indexOfLastPost, items, postPerPage]);


    // const setPage = (e) => {
    //     setCurrentpage(e);
    //   };
      
    return(
        
        <Positioner className={ScrollActive ? 'flexible' : null}> 
            <CategoryNavigation>
                {categoryNavigation[1] === 'all' ? categoryNavigation[0] : categoryNavigation.join(' > ')}
            </CategoryNavigation>

            {pcategory !== 'animal' ?
            <>
            {product.product
            .filter(item => item.pcategory.includes(pcategory) )
            .map((item , index ,array)=>
            <ItemList item={item} key={index} array={array} setArray={setArray} />)}
            </>
            :
            <>
            {product.product
            .map((item , index)=>
            <AllItemList item={item} key={index} />)}
            </>
            }
            
            
            {/* 베스트 상품 */}


            {pcategory === 'best' ?
            <>
            {bestSorting().map((item) => 
            <AllItemList item={item} id={item.id} pcategory={pcategory} />)}
            </>
            
            :
            <>
            
            </>
            }

            {/* <Pagination page={currentpage} count={count} setPage={setPage} />    */}

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

const StyledLink = styled(Link)`
text-decoration : none;
&:focus, &:hover, &:visited, &:link, &:active {
        text-decoration: none;
        color : #000000;
    }


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

