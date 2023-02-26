import React ,{useCallback, useEffect, useState} from "react";
import styled from "styled-components";
import {Link} from 'react-router-dom'
/*상품 불러오기*/
import product from '../../JSON/product.json'
import productImg from '../../JSON/productImg.json'
/* ItemPagination */
import Pagination from './Paging';

function ItemList({item}){


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

export default function Menu({pcategory , ScrollActive , categoryNavigation}){

    /*페이징 */
    const [arr,setArr] = useState([]);  // 리스트에 나타낼 아이템들

    useEffect(()=>{
        setArr(product.product.filter(item => item.pcategory.includes(pcategory) ))
    },[pcategory])

    const [count, setCount] = useState(0); // 아이템 총 개수
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지. default 값으로 1
    const [postPerPage] = useState(10); // 한 페이지에 보여질 아이템 수 
    const [indexOfLastPost, setIndexOfLastPost] = useState(0); // 현재 페이지의 마지막 아이템 인덱스
    const [indexOfFirstPost, setIndexOfFirstPost] = useState(0); // 현재 페이지의 첫번째 아이템 인덱스
    const [currentPosts, setCurrentPosts] = useState([]); // 현재 페이지에서 보여지는 아이템들
    const [list,setList] = useState([])
    console.log(count,currentPage)
    // items호출

    //객체 -> 배열
    useEffect(()=>{
    setList(Object.values(arr))
    },[arr])

    useEffect(() => {
    setCount(Object.keys(arr).length);
    setIndexOfLastPost(currentPage * postPerPage);
    setIndexOfFirstPost(indexOfLastPost - postPerPage);
    setCurrentPosts(list.slice(indexOfFirstPost,indexOfLastPost));
    }, [currentPage, indexOfFirstPost, indexOfLastPost, arr, list, postPerPage]);

    const setPage = (e) => {
        setCurrentPage(e);
    };

    /*조회수 순으로정렬*/
    const bestSorting = () => (
        currentPosts.sort((a,b) => {
        return b.pview - a.pview
    }))

    return(
        
        <Positioner className={ScrollActive ? 'flexible' : null}> 
            <CategoryNavigation>
                {categoryNavigation[1] === 'all' ? categoryNavigation[0] : categoryNavigation.join(' > ')}
            </CategoryNavigation>

            {pcategory === '-' ?
            <>
            {bestSorting().map((item) => 
            <ItemList item={item} id={item.id} pcategory={pcategory} />)}
            
            </>
            :
            <>
            {currentPosts
            .filter(item => item.pcategory.includes(pcategory) )
            .map((item , index )=>
            <ItemList item={item} key={index} />)}
            </>
            }
            <Pagination page={currentPage} count={count} setPage={setPage} />   

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

