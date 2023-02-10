import React , {useState , useEffect ,useRef} from 'react';

import styled from 'styled-components';

/* Header import */
import Header from '../components/Header/Header';

/* Top import */
import Top from '../components/Top/Top';

/* Nav import */
import Nav from '../components/Nav/Nav';

/* Item import */
import Item from '../components/Item/Item';

/* Footer import */
import Footer from '../components/Footer/Footer';

function Home(){

    const [pcategory , setPcategory] = useState('animal')

    const clickPcategory = (pcategory) => {
        setPcategory(pcategory)
    }    

    /*스크롤 이벤트 */

    const [ScrollY, setScrollY] = useState(0); // window 의 pageYOffset값을 저장 
    const [ScrollActive, setScrollActive] = useState(false); 

    const handleScroll = () => { 
        if(ScrollY > 120) {
            setScrollY(window.pageYOffset);
            setScrollActive(true);
        } else {
            setScrollY(window.pageYOffset);
            setScrollActive(false);
        }
    }

    useEffect(() => {
        function scrollListener() {  window.addEventListener("scroll", handleScroll); } //  window 에서 스크롤을 감시 시작
        scrollListener(); // window 에서 스크롤을 감시
        return () => { window.removeEventListener("scroll", handleScroll); }; //  window 에서 스크롤을 감시를 종료
    });

    return(
    <>
        <Container>
        <Top ScrollActive={ScrollActive} /> 
        <Header ScrollActive={ScrollActive}/> 

        <Nav pcategory={pcategory} clickPcategory={clickPcategory} />
        <Item pcategory={pcategory} ScrollActive={ScrollActive}/>
        
        </Container>
        
        <Footer />
    </>
    )
}

export default Home;

const Container = styled.div`

position: relative;
padding: 0px;
border: 0px;

width : 100%;
height: 200%;
`