package com.mygdx.alphabetizergame.Helpers;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by angelo_2 on 12/2/2014.
 */
public class WordGenerator {

    List<String> generatedWords;
    List<List <String>> alphaList;
    List<String> deDuplicatedGeneratedWords;


    String[] Afourletterparted;
    String[] Bfourletterparted;
    String[] Cfourletterparted;
    String[] Dfourletterparted;
    String[] Efourletterparted;
    String[] Ffourletterparted;
    String[] Gfourletterparted;
    String[] Hfourletterparted;
    String[] Ifourletterparted;
    String[] Jfourletterparted;
    String[] Kfourletterparted;
    String[] Lfourletterparted;
    String[] Mfourletterparted;
    String[] Nfourletterparted;
    String[] Ofourletterparted;
    String[] Pfourletterparted;
    String[] Qfourletterparted;
    String[] Rfourletterparted;
    String[] Sfourletterparted;
    String[] Tfourletterparted;
    String[] Ufourletterparted;
    String[] Vfourletterparted;
    String[] Wfourletterparted;
    String[] Xfourletterparted;
    String[] Yfourletterparted;
    String[] Zfourletterparted;

    List<String> A4list;
    List<String> B4list;
    List<String> C4list;
    List<String> D4list;
    List<String> E4list;
    List<String> F4list;
    List<String> G4list;
    List<String> H4list;
    List<String> I4list;
    List<String> J4list;
    List<String> K4list;
    List<String> L4list;
    List<String> M4list;
    List<String> N4list;
    List<String> O4list;
    List<String> P4list;
    List<String> Q4list;
    List<String> R4list;
    List<String> S4list;
    List<String> T4list;
    List<String> U4list;
    List<String> V4list;
    List<String> W4list;
    List<String> X4list;
    List<String> Y4list;
    List<String> Z4list;


    public WordGenerator(){
        generatedWords = new ArrayList<String>();

        String AfourletterWords = "abba abbe abbr abed abet able ably abra abut acct aced aces ache achy acid aclu acme acne acre acts adam adar adds advt afar afro agar aged ages agni agog ague ahem ahoy aide aids ails aims airs airy ajar akin alan alar alas alba albs ales alga ally alma alms aloe alps also alto alum amen amid ammo amok amps ands anet anew ankh anna anne anon ansi ante anti ants anus aped apes apex apse aqua arab arch arcs area ares argo aria arid arks arms army arse arts arty arum asap ashy asia asks asps assn asst atom atop attn atty aube auks aunt aura auto avdp aver aves avid avie avis avow away awed awes awls awns awol awry axed axel axes axis axle axon ayah ayes";
        String BfourletterWords = "baal baas baba babe babu baby bace bach back bade bads baff baft bags baht bail bain bait bake bald bale bali balk ball balm banc band bane bang bank bans barb bard bare barf bark barm barn bars base bash bask bass bast bate bath bats batz baud bauk bawd bawl bawn baya bays bdrm bead beak beal beam bean bear beat beau beck bede beds beef beem been beep beer bees beet bega begs behn belk bell belt bema bend bene bens bent bere berg berm bess best beta bete bets bevy beys bias bibb bibs bice bide bids bier biff biga bigg bike bikh bile bilk bill bind bine bing bink bins biol bion bios bird birk birl birr birt bise bish bisk bite bito bits bitt blab blae blah blat blay bldg blea bleb bled blee blek blet blew blin blip blob bloc blot blow blub blue blur blvd boar boas boat bobs boca boce bock bode bods body boer boes boff bogs bogy boil boke bola bold bole boll boln bolo bolt bomb bona bond bone bong bono bons bony boob book boom boon boor boos boot bops bord bore born bort bosa bosh bosk boss bote both bots boud bouk boul boun bour bout bowl bows boxy boyo boys boza bozo brad brae brag bran bras brat braw bray bred bren bret brew brid brie brig brim brin brio brit brob brog bros brow bruh brun brut buat bubo bubs buck buds buff bufo bugs buhl bulb bulk bull bump bums bund bung bunk bunn buns bunt buoy burg burh burl burn burp burr burs burt bury bush busk buss bust busy buts butt buys buzz byes byre byss byte";
        String CfourletterWords = "caas cabs cack cade cadi cads cady cafe cage cagy cake caky calc calf cali calk call calm calx came camp cams cand cane cans cant cany cape caps card care carf cark carl carp cars cart casa case cash cask cass cast cata cate cats cauf cauk caul cave cavy cawk caws cayo cays ceca cede cees ceil cell celt cent cere cero cert cess cest cete chab chad chak cham chap char chat chaw chef chem ches chew chez chia chic chid chin chip chit chop chou chow chub chud chug chum ciao cill cima cine cion circ cist cite city cive cize clad clam clan clap claw clay clee clef cleg clem clew clio clip clod clog clop clot cloy club clue clum cmdg coag coak coal coat coax cobs coca cock coco coda code cods coed cogs coho coif coil coin coir coit coke cola cold cole coll colp colt coly coma comb come comp cond cone conf cong conj conk conn cons cont cony cook cool coom coon coop coos coot cope cops copy cora corb cord core corf cork corm corn corp cosh coss cost cosy cote cots coup cove cowl cows coxa cozy crab crag cram cran crap craw cray cree crew crib cric crop crow crud crup crus crut crux ctrl cuba cube cubs cuca cuds cued cues cuff cuke cull culm cult cund cunt cups curb curd cure curl curr curs curt cusk cusp cuss cute cuts cyan cyma cyme cyon cyst czar";
        String DfourletterWords = "dabb dabs dace dada dade dado dads daff daft dago dais dale dalf dame damn damp dams dana dane dang dank dare darg dark darn darr dart dase dash data date daub dauk daun dauw dave dawe dawk dawn days daze dbms dead deaf deal dean dear deas debs debt deck deco dede deed deem deep deer dees deev deft defy degu deil deis deja dele delf deli dell deme demi demo demy dens dent deny dept dere derf derk derm dern desk dess deus deux deva deve devi dews dewy deye deys dhow diag dial diam dian dias dibs dice dich dick dict dido didy died diem dies diet digs dika dike dill dime dims dine ding dink dins dint dips dipt dire dirk dirl dirt disc dish disk dite ditt diva dive dizz djin doab doat dock docs dodd dodo doer does doff doge dogs dogy doit dojo doko dole dolf doll dolt dome doms dona done dong doni dons doom doop door dope dopy dorm dorn dorp dorr dors dory dose doss dost dote doth dots doty douc dour dout dove dowl down dows doxy doze dozy drab drad drag dram drat draw dray dree dreg drek drew drey drib drie drip droh drop drow drub drug drum drys duad dual duan dubb dubs duce duck duct dude duds duel dues duet duff dugs duke dull duly dumb dump dune dung dunk duns dunt duos dupe dura dure durn duse dusk dust duty dyad dyas dyed dyer dyes dyke dyne";
        String EfourletterWords = "each eale eame earl earn ears ease east easy eath eats eaux eave ebbs ebon eccl eche echo ecol econ ecru ecus edam edda eddy eden edge edgy edit eeke eels eely eery effs efts egad egal eger eggs egis egos egre eigh eild eire ejoo eked ekes elan elds elhi elix elke elks ells elms elmy else elul elve emeu emew emir emit emmy emus emyd encl ends engr enow envy eons epee epen epha epic epos eras erat ergo ergs eric erie erin erke erme erne erns eros errs erse ersh erst eses esox espy esse etch ethe etna etui etym euge eugh eval even ever eves evet evil ewer ewes ewry exam exec exes exit exon expo eyas eyed eyen eyer eyes eyet eyle eyne eyot eyra eyre eyry";
        String FfourletterWords = "face fact fade fads fady fags fail fain fair fait fake falk fall falx fame fand fane fang fans fard fare farl farm faro fart fash fast fate fats faun faut faux fawe fawn fays faze feal fear feat feck feds feed feel fees feet fehm fele fell felt feme fend fens feod fere ferm fern fers fess fest feta fete feud fiar fiat fibs fica fice fico fide fido fids fief fife figs fiji fike file fill film fils find fine fink finn fins fint fire firk firm firs fisc fish fisk fist fits fitt fitz five fixe fizz flab flag flak flam flan flap flat flaw flax flay flea fled flee flet flew flex flip flit flix floe flog flon flop flow flub flue flus flux foal foam fobs foci foes foge fogs fogy foil foin fold folk fond fone font food fool foot fops fora ford fore fork form fort foul four fowl foxy fozy frab frag frap frat frau fray fred free fren fret frig frim frit friz froe frog from frow frug fuar fubs fuci fuck fuds fuel fuff fuga fugh fugs fuji full fume fumy fund funk furl furs fury fuse fuss fust fuze fuzz fyke fyrd";
        String GfourletterWords = "gabs gaby gade gads gael gaff gaga gage gags gain gait gala gale gall gals galt game gamp gams gamy gane gang gaol gape gaps gapy garb gard gare gars gary gash gasp gast gate gats gaud gaul gaur gave gawk gawn gays gaze geal gean gear geat geck gedd geed geek geer gees geet geez geic gein geld gels gelt gems gena gene gens gent genu geog geol geom gere germ gern gery gest geth gets geum ghat ghee gibe gibs gide gift gigs gila gild gile gill gilt gimp ging ginn gins gips gird gire girl girn girt gise gist gite gith give glad glee gleg glen glew gley glib glim glob glom glop glow glue glum glut glyn gnar gnat gnaw gnew gnof gnow gnus goad goaf goal goar goat gobs goby gode gods goel goen goer goes goff gogo gold golf goll gome gone gong good goof gook goon goop goos goot gord gore gorm gory gosh goss gote goth goud gour gout gove govt gowd gowk gowl gown goys grab grad graf gram gras gray gree gres gret grew grey grid grig gril grim grin grip gris grit grog gros grot grow grub gruf grum guam guan guar guck guff guhr guib guid gula gule gulf gull gulp gult guly gump gums guna gung gunk guns gurl gurt guru gush gust guts guys guze gybe gyle gyms gyps gyre gyri gyro gyse gyte gyve";
        String HfourletterWords = "haaf haak haar hack hade hadj haft hags hahs haik hail hair haji hajj hake hale half halk hall halm halo halp hals halt hame hams hand hang hank haps hard hare hark harl harm harp hart hary hase hash hask hasp hast hate hath hats haul haum haut have hawk hawm haws haye hays haze hazy head heal heam heap hear heat hebe heck heed heel heep heer heft heil heir held hele hell helm help heme hemp hems heng hens hent herb herd here herl hern hero herr hers hert hery hesp hest hete heuk hewe hewn hews hexa heyh hick hide hied hies high hike hile hill hilt hind hine hink hint hipe hips hire hirs hisn hiss hist hits hive hizz hoar hoax hobo hobs hock hods hoed hoer hoes hogh hogo hogs hoit hoke hold hole holm holp holt holy home homo homy hond hone hong honk hont hood hoof hook hool hoom hoop hoot hope hopi hops hora hore horn hors hose hosp host hote hots houp hour hove howe howl howp hows hubs huch huck hued huer hues huff huge hugs hugy huke hula hulk hull hump hums hung hunk huns hunt hurl hurr hurt hush husk huso huts huzz hyde hyen hyke hymn hyne hype hypo";
        String IfourletterWords = "iamb ibex ibid ibis icbm iced ices icky icon idea idee idem ideo ides idle idly idol idyl ieee iffy ihvh ikon ilex ilia ilke ilks ills illy imam iman impi imps inca ince inch inde inee info inia inks inky inly inne inns inro inst intl into intr ions iota iowa ipso iran iraq ired ires iris irks iron irpe isis isle isms ital itch item iter iuds iwis ixia izar";
        String JfourletterWords = "jabs jack jade jagg jags jail jain jake jako jamb jams jane jant jape jarl jars jasp jato java jawn jaws jawy jays jazz jean jeat jeel jeep jeer jeez jefe jehu jell jerk jess jest jesu jets jeux jews jibb jibe jibs jiff jigs jill jilt jimp jink jinn jins jinx jive jobs jock joes joey jogs john joie join joke jole joll jolt jose josh joso joss jota jots jouk joul jour jove jowl joys juan juba jube judo judy juga juge jugs juju juke juli july jump june junk juno jupe jura jure jury just jute juts";
        String KfourletterWords = "kadi kage kagu kail kain kaka kale kali kama kame kami kand karn kart kate kava kawn kayo kays keck keel keen keep kegs keir keld kele kell kelp kelt kemb kemp keno kens kent kepi kept kerb kerf kerl kern kers kess kest keta keys khan kibe kiby kick kids kier kiev kike kill kiln kilo kilt kind kine king kink kino kins kipe kips kirk kish kiss kist kite kith kits kiva kive kiwi knab knag knap knar knaw knee knew knit knob knop knor knot know knox knur koan koba koel koff kohl kola kong kook koto kris ksar kuda kudo kudu kung kurd kwhr kyar kyat kyaw kyke";
        String LfourletterWords = "laas labs lace lack lacy lade lads lady laft lags laic laid lain lair lait lake lakh laky lalo lama lamb lame lamm lamp lams land lane lang lank lant laos lapp laps lard lare lark lars lary lash lask lass last lata late lath laud laus lava lave lawe lawm lawn laws lays laze lazy lead leaf leak leal leam lean leap lear leas leat lech lect leed leef leek leep leer lees leet left lege legs leis leks leme lena lend lene leno lens lent leod leon leos lere lese less lest lete lets leva leve levi levo levy lewd leys liar lias libs lice lich lick lido lids lied lief lien lier lies lieu life lift lige like lill lilt lily lima limb lime limn limo limp limu limy lind line ling link lino lins lint liny lion lips lira lire lisp liss list lite lith lits live lixt liza load loaf loam loan lobe lobo lobs loca loch loci lock loco lode loft loge logo logs logy loin loir loke loki loll loma lond lone long loob loof look lool loom loon loop loos loot lope lops lord lore lori lorn lory lose loss lost lote loth loto lots loud louk loup lour lout love lowh lowk lown lows luau lube luce luck lucy lues luff luge lugs luke lull lulu lump luna lune lung lunk lunt luny lure lurg lurk lush lusk lust lute luth luxe lyam lyes lyne lynx lyra lyre";
        String MfourletterWords = "maad maat mace mach mack macs made mads mage magi mags maha maia maid mail maim main make maki mala male mali mall malm malt mama mand mane mano mans manu manx many maps mara marc mare mark marl mars mart marx mary mase mash mask mass mast mate math mats matt maty maud maul maut mawk maws maxi maya mayo mays maze mazy mead meak meal mean mear meas meat meaw mech mede meed meek meer meet mega mein meld mell melt memo mend mens ment menu meow merd mere merk merl mero mesa mesh mess mest meta mete meth meum meve mewl mews mias mibs mica mice mich mick mico mida midi mids mien miff migo migs mike mild mile milk mill mils milt mime mina mind mine ming mini mink mins mint minx miny mira mire mirk mirv miry misc mise miso miss mist misy mite mitt mitu mity mixt mktg moan moas moat mobs mock moco mode modi modo mods mody moff moha moho mohr moil moke moky mola mold mole moll molt moly mome moms mona mone monk mono mons mont mony mood moon moor moos moot mope mops mopy mora more morn moro mort mosk moss most mote moth moto mots moue moun move mowe mown mows moxa moya msec muce much muck muds muff mugs mule mull mumm mump mums mund mung muon mure murk murr musa muse mush musk muss must mute mutt muxy myna myth myxa";
        String NfourletterWords = "nabk nabs nags naid naif naik nail nais nake nale nall name namo naos nape naps napu narc nard nare nark nart nary nasa nash nath natl nato naut nave navy nawl nays nayt naze nazi neaf neal neap near neat nebs neck need neer neif nems neon nepa nerd nere nero nese nesh ness nest nets neve nevi news newt next nias nibs nice nick nide nief nigh nile nill nils nilt nims nine nips nipt nisi nits nixy noah nobs nock node nods noel noes nogs noie noil noir nole noll nolo nolt noma nome noms none nook noon noot nope norm norn nose nosh nost nosy nota note nots nott noun nous nova novo nowd nows nowt nubs nude nuke null numb nuns nurl nuts nyas";
        String OfourletterWords = "oafs oaks oaky oars oary oast oath oats obbe obey obis obit oboe obol ocra odal odds odes odic odin odor odyl ofay offs ogam ogee ogle ogre ohed ohio ohms oils oily oink oint okay oker okie okra olay olds olea oleo oles olid olio olla olpe omen omer omit once onde ones only onto onus onyx oohs oops ooze oozy opah opal opec open opes opie opts opus opye oral orbs orby orca orch orcs ordo ores orfe orgy orig orle orlo orth orts oryx osar oslo ossa osse otic otis otto ouch ours ouse oust outs ouze ouzo oval oven over ovid ovum owch owed owel owen owes owls owns owre owse oxen oxes oxid oyer oyes oyez";
        String PfourletterWords = "paas paca pace pack paco pacs pact pacu pads page pahi paid pail pain pair pais pale pali pall palm palo palp pals paly pane pang pans pant papa pape paps para pard pare park parr pars part pash pask paso pass past pate path pats paul paum pave pavo pawk pawl pawn paws payn pays peag peak peal pean pear peas peat peba peck peds peed peek peel peen peep peer pees pegm pegs pein peke pela pelf pell pelt pend penk pens pent peon pepo peps pere peri perk perm pern pers pert peru pery pese peso pest pets pews phew phiz phys phyz pial pian pica pice pici pick pics pied pier pies piet pigg pigs pika pike pile pill pily pima pimp pina pine ping pink pins pint piny pion piot pipa pipe pips pipy pirl pirn pisa pise pish piss pist pita pith pits pity pius pixy pkwy plan plat play plea pled pley plim ploc plod plop plot plow ploy plug plum plus pnyx poak pock poco pods poem poet pogy poke poky pole polk poll polo pols polt poly pome pomp pond pone pong pons pony pood pooh pool poon poop poor pope pops pore pork porn port pory pose posh poss post posy pots pott pouf poup pour pout powp pows poze prad pram prat pray prep pres prey prie prig prim pris prix proa proc prod prof prog prom pron prop pros prow prox psst pubs puce puck puds pudu pued puer puet puff pugh pugs puit puke pule pull pulp pult pulu puma pume pump pumy puna pung punk puns punt puny puoy pupa pupe pups pure puri purl purr push puss puts putt pyet pyin pyla pyne pyot pyre pyro";
        String QfourletterWords = "quin quip quit quiz quay quid";
        String RfourletterWords = "raca race rach rack racy rade rads raff raft raga rage rags raia raid rail rain raip rais raja rake raki rale rami ramp rams rana rand rang rani rank rant rape raps rapt rara rare rase rash rasp rata rate rath rats rave raws rays raze razz rcpt read reak real ream reap rear rebs recd reck recs rede redo reds reed reef reek reel reem refs reft reif reim rein reis reit rely reme rems rend reng reno rent reps rese resp rest retd rete reve revs rewe reyn rhea rhob rhus rial ribs rice rich rick ride rids rief riel rife riff rift rigs rile rill rily rima rime rims rimy rind rine ring rink riot ripe rips rise rish risk rist rite ritz rive road roam roan roar robe robs rock rocs rode rods rody roed roes roil roin roke roky role roll rome romp roms rong ront rood roof rook room roon roop root rope ropy rory rosa rose ross rost rosy rota rote roto rots roty roue rouk roun roup rout roux rove rown rows rube rubs ruby ruck rudd rude rued ruer rues ruff ruft ruga rugs ruin rukh rule ruly rump rums rune rung runs runt ruse rush rusk russ rust ruth ruts ryal ryas ryes rynd ryot rysh ryth";
        String SfourletterWords = "saan sack sacs sadh sadr safe saga sage sago sags sagy saic said sail saim sain sake saki sale salm salp salt same samp sand sane sang sank sans saps sard sari sark sarn sart sash sass sate sauf sauh saul saur saut save sawn saws says scab scad scag scam scan scar scat scil scop scot scow scry scud scug scum scup scur scut scye sdan seah seak seal seam sean sear seas seat seck secs sect seed seek seel seem seen seep seer sees seet sego seid seke seld self sell sely seme semi send sens sent sept sera serb sere serf serr sess seta sets sett sewe sewn sews sext sexy seye seyh shab shad shag shah sham shat shaw shay shed shes shet shew shie shim shin ship shit shiv shmo shod shoe shog shoo shop shot show shug shul shun shut siam sibs sice sich sick sics sida side sift sigh sign sike sikh sile silk sill silo silt sima simp sine sing sinh sink sins sipe sips sipy sire sirs sirt sise siss sist site sith sits situ sitz siva size sizy skag skar skat skee skeg sken skep skew skid skim skin skip skis skit skua skue skun skys slab slag slam slap slat slav slaw slay sled slee slep slew sley slid slik slim slip slit slob sloe slog sloo slop slot slow slub slue slug slum slur slut smee smew smit smog smug smut snag snap snar snaw sneb sned snet snew snib snig snip snit snob snod snot snow snub snug soak soal soam soap soar sobs sock soda sods sofa sofi soft soho soil soja soke soko sola sold sole soli solo soly soma some sond song sons soon soot sope soph sops sora sorb sord sore sori sorn sors sort sory soss sote sots soul soun soup sour sous sout sowl sown sows soya soys spad spae span spar spas spat spaw spay spec sped sper spet spew spic spin spit spot spry spud spue spun spur sput stab stag stal star stat staw stay sted stee steg stem step stet stew stey stir stoa stop stor stot stow stre stub stud stum stun stut stye styx subs such suck sudd suds sued suer sues suet suey suez sufi suit suji sula sulk sull sulu sumo sump sums sung sunk sunn suns supe sups supt sura surd sure surf susu swab swad swag swal swam swan swap swat sway swig swim swob swom swop swum syce syke syle sync syne syrt syth";
        String TfourletterWords = "tabs tack taco tact tads tael taen tags taha tahr tail tain tait take talc tale tali talk tall tame tamp tams tana tang tank tans tant taos tapa tape taps tare tarn taro tarp tars tart task tass tate tath tats tatt tatu taur taut taws taxi tbsp tead teak teal team tear teas teat tech teds teed teek teel teem teen tees teil tell temp tend tene tens tent term tern terr test tete teuk text thai thak than thar that thaw thea thee them then thew they thin this thor thou thro thru thud thug thus tiar tice tick tics tide tidy tied tier ties tiff tift tigh tike tile till tilt time tind tine ting tink tins tint tiny tipi tips tire tirl tiro tith titi tits tivy tiza tnpk toad toat toby toco tody toed toes toff toft tofu toga togo togs toil toke tola told tole toll tolt tolu tomb tome toms tone tong tons tony took tool toom toon toot tope toph topi tops tora torc tore tori torn toro tors tort tory tose tosh toss tost tota tote toto tots toty tour tout town tows towy toys toze tozy trad tram trap tray tree tref trek tren tret trew trey trig trim trio trip trod tron trop trot trow troy trub true trug tsar tuba tube tubs tuch tuck tuet tufa tuff tuft tugs tule tull tump tuna tune tunk tuns tupi tups turd turf turk turm turn tush tusk tuts tutu tuum tuza twas twat tway twey twig twin twit twos tydy tyer tyke tymp tynd tyne tyny type typo tyre tyro tzar";
        String UfourletterWords = "udal ufos ughs ugli ugly ukes ulan ulna ulva umbe umbo umps unau unbe unce unci unco unde undo undy unio unit univ unix unke unto unty unum upas upon ural urao urbs urds urdu urea urge uric urim urns urox urry ursa urus urva used usee user uses ussr utah utas utes utia utis utro uvea uvic";
        String VfourletterWords = "vade vail vain vair vale vamp vane vang vans vant vara vare vari vark vary vasa vase vast vats vaut veal veda veep veer vees vega vehm veil vein vela veld vele vell vena vend vent verb verd vers vert very vese vest veto vets vial vias vice vide vied vier vies view vild vile vill vims vina vine vino vins viny viol vips vire visa vise vita viva vive vivo voce void vole volt vote vows vrow vugg vugh vugs vyce";
        String WfourletterWords = "waag wack wacs wadd wade wadi wads wady waeg waft wage wags waid waif wail wain wair wait wake wakf wald wale walk wall walm walt waly wamp wand wane wang want wany wapp ward ware wark warm warn warp wars wart wary wase wash wasp wast wats watt waul waur wave wavy wawe wawl waxy wayk ways weak weal wean wear webs weds weed week weel ween weep weet weft weir weka weld wele welk well wels welt wend wene wens went wept were werk wern wert wesh west wets wham whan whap what whee when whet whew whey whig whim whin whip whir whit whiz whoa whom whop whot whur whys wich wick wide wier wife wigg wigs wike wild wile wilk will wilt wily wind wine wing wink wino wins winy wipe wire wiry wise wish wisp wist wite with wits wive wkly woad wode woes woke woks wold wolf woll womb wone wong wont wood woof wook wool woon woos wops word wore work worm worn wort wost wots woul wove wowe wowf wows wrap wraw wray wren wrey wrie wrig writ wull wust wyes wyke wyla wynd wynn wype wyte";
        String XfourletterWords = "xeme xiii xmas xvii xxii xxiv xyst";
        String YfourletterWords = "yack yaks yale yama yamp yams yang yank yaps yard yare yark yarn yarr yate yaud yaul yaup yawd yawi yawl yawn yawp yaws yead yeah yean year yeas yede yeel yegg yelk yell yelp yend yens yerd yerk yern yest yeti yeve yews yghe yids yift yins yipe yips yite yive ymca ymel ynow yode yoga yogi yoit yoke yold yolk yoll yond yoni yore york yote youl your yowe yowl yows yren yuan yuck yuen yuga yuke yuks yule yunx yurt yvel ywar ywca ywis";
        String ZfourletterWords = "zags zaim zain zany zaps zarf zati zeal zebu zeds zees zein zend zero zest zeta zeus zigs zimb zinc zing zink zion zips zobo zoea zoic zona zone zoom zoon zoos zope zori zubr zulu zuni zyme";

        Afourletterparted = AfourletterWords.split(" ");
        Bfourletterparted = BfourletterWords.split(" ");
        Cfourletterparted = CfourletterWords.split(" ");
        Dfourletterparted = DfourletterWords.split(" ");
        Efourletterparted = EfourletterWords.split(" ");
        Ffourletterparted = FfourletterWords.split(" ");
        Gfourletterparted = GfourletterWords.split(" ");
        Hfourletterparted = HfourletterWords.split(" ");
        Ifourletterparted = IfourletterWords.split(" ");
        Jfourletterparted = JfourletterWords.split(" ");
        Kfourletterparted = KfourletterWords.split(" ");
        Lfourletterparted = LfourletterWords.split(" ");
        Mfourletterparted = MfourletterWords.split(" ");
        Nfourletterparted = NfourletterWords.split(" ");
        Ofourletterparted = OfourletterWords.split(" ");
        Pfourletterparted = PfourletterWords.split(" ");
        Qfourletterparted = QfourletterWords.split(" ");
        Rfourletterparted = RfourletterWords.split(" ");
        Sfourletterparted = SfourletterWords.split(" ");
        Tfourletterparted = TfourletterWords.split(" ");
        Ufourletterparted = UfourletterWords.split(" ");
        Vfourletterparted = VfourletterWords.split(" ");
        Wfourletterparted = WfourletterWords.split(" ");
        Xfourletterparted = XfourletterWords.split(" ");
        Yfourletterparted = YfourletterWords.split(" ");
        Zfourletterparted = ZfourletterWords.split(" ");



        A4list = new ArrayList<String>(Arrays.asList(Afourletterparted));
        B4list = new ArrayList<String>(Arrays.asList(Bfourletterparted));
        C4list = new ArrayList<String>(Arrays.asList(Cfourletterparted));
        D4list = new ArrayList<String>(Arrays.asList(Dfourletterparted));
        E4list = new ArrayList<String>(Arrays.asList(Efourletterparted));
        F4list = new ArrayList<String>(Arrays.asList(Ffourletterparted));
        G4list = new ArrayList<String>(Arrays.asList(Gfourletterparted));
        H4list = new ArrayList<String>(Arrays.asList(Hfourletterparted));
        I4list = new ArrayList<String>(Arrays.asList(Ifourletterparted));
        J4list = new ArrayList<String>(Arrays.asList(Jfourletterparted));
        K4list = new ArrayList<String>(Arrays.asList(Kfourletterparted));
        L4list = new ArrayList<String>(Arrays.asList(Lfourletterparted));
        M4list = new ArrayList<String>(Arrays.asList(Mfourletterparted));
        N4list = new ArrayList<String>(Arrays.asList(Nfourletterparted));
        O4list = new ArrayList<String>(Arrays.asList(Ofourletterparted));
        P4list = new ArrayList<String>(Arrays.asList(Pfourletterparted));
        Q4list = new ArrayList<String>(Arrays.asList(Qfourletterparted));
        R4list = new ArrayList<String>(Arrays.asList(Rfourletterparted));
        S4list = new ArrayList<String>(Arrays.asList(Sfourletterparted));
        T4list = new ArrayList<String>(Arrays.asList(Tfourletterparted));
        U4list = new ArrayList<String>(Arrays.asList(Ufourletterparted));
        V4list = new ArrayList<String>(Arrays.asList(Vfourletterparted));
        W4list = new ArrayList<String>(Arrays.asList(Wfourletterparted));
        X4list = new ArrayList<String>(Arrays.asList(Xfourletterparted));
        Y4list = new ArrayList<String>(Arrays.asList(Yfourletterparted));
        Z4list = new ArrayList<String>(Arrays.asList(Zfourletterparted));

        alphaList = new ArrayList<List<String>>(Arrays.asList(A4list, B4list, C4list, D4list, E4list, F4list, G4list, H4list, I4list, J4list, K4list, L4list, M4list, N4list, O4list, P4list, Q4list, R4list, S4list, T4list, U4list, V4list, W4list, X4list, Y4list, Z4list));

    }

    public void setGeneratedWords(){
        generatedWords.clear();

        int count;
        Random genRandom = new Random();
        int randomCount1 = genRandom.nextInt(5 - 1 + 1) + 1;
        int randomCount2 = genRandom.nextInt(5 - 1 + 1) + 1;

        int randomLetter1 = genRandom.nextInt((25 - 0) + 1) + 0;
        int randomLetter2 = genRandom.nextInt((25 - 0) + 1) + 0;
        int randomLetter3 = genRandom.nextInt((25 - 0) + 1) + 0;


        for(count = 0; count != randomCount1; count++){
            Collections.shuffle(alphaList.get(randomLetter1));
            generatedWords.add(alphaList.get(randomLetter1).get(0));
            //alphaList.get(randomLetter1).remove(0);
        }
        for(count = 0; count != randomCount2 && generatedWords.size() != 5; count++){
            Collections.shuffle(alphaList.get(randomLetter2));
            generatedWords.add(alphaList.get(randomLetter2).get(0));
            //alphaList.get(randomLetter2).remove(0);

        }
        deDuplicatedGeneratedWords = new ArrayList<String>(new LinkedHashSet<String>(generatedWords));
        for (count = 0; deDuplicatedGeneratedWords.size() != 5; count++) {
            Collections.shuffle(alphaList.get(randomLetter3));
            deDuplicatedGeneratedWords.add(alphaList.get(randomLetter3).get(0));
            //alphaList.get(randomLetter3).remove(0);
        }
        Gdx.app.log("Game", randomCount1 + "" + randomCount2 + "");

    }

    public List<String> getGeneratedWords(){

        return deDuplicatedGeneratedWords;
    }
}
